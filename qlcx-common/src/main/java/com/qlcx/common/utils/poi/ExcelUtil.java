package com.qlcx.common.utils.poi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qlcx.common.annotation.Excel;
import com.qlcx.common.annotation.Excels;
import com.qlcx.common.annotation.Excel.ColumnType;
import com.qlcx.common.annotation.Excel.Type;
import com.qlcx.common.config.Global;
import com.qlcx.common.core.domain.AjaxResult;
import com.qlcx.common.core.text.Convert;
import com.qlcx.common.exception.BusinessException;
import com.qlcx.common.utils.DateUtils;
import com.qlcx.common.utils.StringUtils;
import com.qlcx.common.utils.reflect.ReflectUtils;

/**
 * Xử lý liên quan đến Excel
 */
public class ExcelUtil<T>
{
    private static final Logger log = LoggerFactory.getLogger(ExcelUtil.class);

    /**
     * Số hàng tối đa trong trang tính Excel, mặc định là 65536
     */
    public static final int sheetSize = 65536;

    /**
     * Tên trang tính
     */
    private String sheetName;

    /**
     * Loại Xuất file Excel (EXPORT: export data; IMPORT: import template)
     */
    private Type type;

    /**
     * Đối tượng sổ làm việc
     */
    private Workbook wb;

    /**
     * Đối tượng trang tính
     */
    private Sheet sheet;

    /**
     * Danh sách phong cách
     */
    private Map<String, CellStyle> styles;

    /**
     * Nhập và xuất danh sách dữ liệu
     */
    private List<T> list;

    /**
     * Danh sách chú thích
     */
    private List<Object[]> fields;

    /**
     * Đối tượng thực thể
     */
    public Class<T> clazz;

    public ExcelUtil(Class<T> clazz)
    {
        this.clazz = clazz;
    }

    public void init(List<T> list, String sheetName, Type type)
    {
        if (list == null)
        {
            list = new ArrayList<T>();
        }
        this.list = list;
        this.sheetName = sheetName;
        this.type = type;
        createExcelField();
        createWorkbook();
    }

    /**
     * Đối với biểu mẫu excel, tên chỉ mục đầu tiên được chuyển đổi thành danh sách theo mặc định
     *
     * @param là luồng đầu vào
     * Bộ sưu tập @return sau khi chuyển đổi
     */
    public List<T> importExcel(InputStream is) throws Exception
    {
        return importExcel(StringUtils.EMPTY, is);
    }

    /**
     * Chuyển đổi tên chỉ mục bảng được chỉ định sang biểu mẫu excel thành danh sách
     *
     * @param Tên chỉ mục bảng sheetName
     * @param là luồng đầu vào
     * Bộ sưu tập @return sau khi chuyển đổi
     */
    public List<T> importExcel(String sheetName, InputStream is) throws Exception
    {
        this.type = Type.IMPORT;
        this.wb = WorkbookFactory.create(is);
        List<T> list = new ArrayList<T>();
        Sheet sheet = null;
        if (StringUtils.isNotEmpty(sheetName))
        {
            // Nếu tên trang tính được chỉ định, nội dung trong trang tính được chỉ định sẽ được lấy.
            sheet = wb.getSheet(sheetName);
        }
        else
        {
            // Nếu tên trang tính đến không tồn tại, nó sẽ mặc định là trang tính đầu tiên.
            sheet = wb.getSheetAt(0);
        }

        if (sheet == null)
        {
            throw new IOException("File sheet does not exist");
        }

        int rows = sheet.getPhysicalNumberOfRows();

        if (rows> 0)
        {
            // Xác định một bản đồ để lưu trữ số sê-ri và trường của cột excel.
            Map<String, Integer> cellMap = new HashMap<String, Integer>();
            // Lấy tiêu đề bảng
            Row heard = sheet.getRow(0);
            for (int i = 0; i <heard.getPhysicalNumberOfCells(); i++)
            {
                Cell cell = heard.getCell(i);
                if (StringUtils.isNotNull(cell))
                {
                    String value = this.getCellValue(heard, i).toString();
                    cellMap.put(value, i);
                }
                else
                {
                    cellMap.put(null, i);
                }
            }
            // Chỉ xử lý dữ liệu để lấy tất cả các trường của lớp.
            Field[] allFields = clazz.getDeclaredFields();
            // Xác định một bản đồ để lưu trữ số thứ tự và trường của cột.
            Map<Integer, Field> fieldsMap = new HashMap<Integer, Field>();
            for (int col = 0; col <allFields.length; col++)
            {
                Field field = allFields[col];
                Excel attr = field.getAnnotation(Excel.class);
                if (attr != null && (attr.type() == Type.ALL || attr.type() == type))
                {
                    // Đặt thuộc tính trường riêng của lớp để có thể truy cập được.
                    field.setAccessible(true);
                    Integer column = cellMap.get(attr.name());
                    fieldsMap.put(column, field);
                }
            }
            for (int i = 1; i <rows; i++)
            {
                // Bắt đầu tìm nạp dữ liệu từ hàng thứ hai, hàng đầu tiên mặc định là tiêu đề bảng.
                Row row = sheet.getRow(i);
                T entity = null;
                for (Map.Entry<Integer, Field> entry: fieldsMap.entrySet())
                {
                    Object val = this.getCellValue(row, entry.getKey());

                    // Nếu không có phiên bản nào tồn tại, hãy tạo một phiên bản mới.
                    entity = (entity == null? clazz.newInstance(): entity);
                    // Lấy trường của cột tương ứng từ bản đồ.
                    Field field = fieldsMap.get(entry.getKey());
                    // Lấy kiểu và đặt giá trị theo kiểu đối tượng.
                    Class<?> fieldType = field.getType();
                    if (String.class == fieldType)
                    {
                        String s = Convert.toStr(val);
                        if (StringUtils.endsWith(s, ".0"))
                        {
                            val = StringUtils.substringBefore(s, ".0");
                        }
                        else
                        {
                            String dateFormat = field.getAnnotation(Excel.class).dateFormat();
                            if (StringUtils.isNotEmpty(dateFormat))
                            {
                                val = DateUtils.parseDateToStr(dateFormat, (Date) val);
                            }
                            else
                            {
                                val = Convert.toStr(val);
                            }
                        }
                    }
                    else if ((Integer.TYPE == fieldType) || (Integer.class == fieldType))
                    {
                        val = Convert.toInt(val);
                    }
                    else if ((Long.TYPE == fieldType) || (Long.class == fieldType))
                    {
                        val = Convert.toLong(val);
                    }
                    else if ((Double.TYPE == fieldType) || (Double.class == fieldType))
                    {
                        val = Convert.toDouble(val);
                    }
                    else if ((Float.TYPE == fieldType) || (Float.class == fieldType))
                    {
                        val = Convert.toFloat(val);
                    }
                    else if (BigDecimal.class == fieldType)
                    {
                        val = Convert.toBigDecimal(val);
                    }
                    else if (Date.class == fieldType)
                    {
                        if (val instanceof String)
                        {
                            val = DateUtils.parseDate(val);
                        }
                        else if (val instanceof Double)
                        {
                            val = DateUtil.getJavaDate((Double) val);
                        }
                    }
                    if (StringUtils.isNotNull(fieldType))
                    {
                        Excel attr = field.getAnnotation(Excel.class);
                        String propertyName = field.getName();
                        if (StringUtils.isNotEmpty(attr.targetAttr()))
                        {
                            propertyName = field.getName() + "." + attr.targetAttr();
                        }
                        else if (StringUtils.isNotEmpty(attr.readConverterExp()))
                        {
                            val = reverseByExp(String.valueOf(val), attr.readConverterExp());
                        }
                        ReflectUtils.invokeSetter(entity, propertyName, val);
                    }
                }
                list.add(entity);
            }
        }
        return list;
    }

    /**
     * Nhập dữ liệu trong nguồn dữ liệu danh sách sang biểu mẫu excel
     *
     * @param Thu thập dữ liệu xuất danh sách 
     * @param sheetName tên của trang tính
     * @return kết quả
     */
    public AjaxResult exportExcel(List<T> list, String sheetName)
    {
        this.init(list, sheetName, Type.EXPORT);
        return exportExcel();
    }

    /**
     * Nhập dữ liệu trong nguồn dữ liệu danh sách sang biểu mẫu excel
     *
     * @param sheetName tên của trang tính
     * @return kết quả
     */
    public AjaxResult importTemplateExcel(String sheetName)
    {
        this.init(null, sheetName, Type.IMPORT);
        return exportExcel();
    }

    /**
     * Nhập dữ liệu trong nguồn dữ liệu danh sách sang biểu mẫu excel
     *
     * @return kết quả
     */
    public AjaxResult exportExcel()
    {
        OutputStream out = null;
        try
        {
            // Lấy ra bao nhiêu tờ.
            double sheetNo = Math.ceil(list.size() / sheetSize);
            for (int index = 0; index <= sheetNo; index++)
            {
                createSheet(sheetNo, index);

                // sản xuất một dòng
                Row row = sheet.createRow(0);
                int column = 0;
                // Viết tên tiêu đề cột của mỗi trường
                for (Object[] os: fields)
                {
                    Excel excel = (Excel) os[1];
                    this.createCell(excel, row, column++);
                }
                if (Type.EXPORT.equals(type))
                {
                    fillExcelData(index, row);
                }
            }
            String filename = encodingFilename(sheetName);
            out = new FileOutputStream(getAbsoluteFile(filename));
            wb.write(out);
            return AjaxResult.success(filename);
        }
        catch (Exception e)
        {
            log.error("Export Excel exception {}", e.getMessage());
            throw new BusinessException("Export Excel failed, please contact the webmaster!");
        }
        finally
        {
            if (wb != null)
            {
                try
                {
                    wb.close();
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
            if (out != null)
            {
                try
                {
                    out.close();
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * Điền dữ liệu excel
     *
     * @param Chỉ mục 
     * @param Hàng ô 
     */
    public void fillExcelData(int index, Row row)
    {
        int startNo = index * sheetSize;
        int endNo = Math.min(startNo + sheetSize, list.size());
        for (int i = startNo; i <endNo; i++)
        {
            row = sheet.createRow(i + 1-startNo);
            // Lấy đối tượng đã xuất.
            T vo = (T) list.get(i);
            int column = 0;
            for (Object[] os: fields)
            {
                Field field = (Field) os[0];
                Excel excel = (Excel) os[1];
                //Đặt thuộc tính riêng của lớp thực thể để có thể truy cập được
                field.setAccessible(true);
                this.addCell(excel, row, vo, field, column++);
            }
        }
    }

    /**
     * Tạo kiểu bảng
     *
     * @param wb đối tượng sổ làm việc
     * Danh sách kiểu @return
     */
    private Map<String, CellStyle> createStyles(Workbook wb)
    {
        // Write each record, each record corresponds to a row in the excel sheet
        Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
        CellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        Font dataFont = wb.createFont();
        dataFont.setFontName("Arial");
        dataFont.setFontHeightInPoints((short) 10);
        style.setFont(dataFont);
        styles.put("data", style);

        style = wb.createCellStyle();
        style.cloneStyleFrom(styles.get("data"));
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font headerFont = wb.createFont();
        headerFont.setFontName("Arial");
        headerFont.setFontHeightInPoints((short) 10);
        headerFont.setBold(true);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(headerFont);
        styles.put("header", style);

        return styles;
    }

    /**
     * Create cell
    */
    public Cell createCell(Excel attr, Row row, int column)
    {
        // Tạo cột
        Cell cell = row.createCell(column);
        // Write column information
        cell.setCellValue(attr.name());
        setDataValidation(attr, row, column);
        cell.setCellStyle(styles.get("header"));
        return cell;
    }

    /**
     * Đặt thông tin ô
     *
     * @param Giá trị ô 
     * @param liên quan đến chú thích  attr
     * Thông tin ô @param cell
     */
    public void setCellVo(Object value, Excel attr, Cell cell)
    {
        if (ColumnType.STRING == attr.cellType())
        {
            cell.setCellType(CellType.NUMERIC);
            cell.setCellValue(StringUtils.isNull(value) ? attr.defaultValue() : value + attr.suffix());
        }
        else if (ColumnType.NUMERIC == attr.cellType())
        {
            cell.setCellType(CellType.NUMERIC);
            cell.setCellValue(Integer.parseInt(value + ""));
        }
    }

    /**
     * Create table style
     */
    public void setDataValidation(Excel attr, Row row, int column)
    {
        if (attr.name().indexOf("Note:") >= 0)
        {
            sheet.setColumnWidth(column, 6000);
        }
        else
        {
            //Đặt chiều rộng cột
            sheet.setColumnWidth(column, (int) ((attr.width() + 0.72) * 256));
            row.setHeight((short) (attr.height() * 20));
        }
        // Nếu thông tin lời nhắc được thiết lập, hãy đưa chuột đến lời nhắc.
        if (StringUtils.isNotEmpty(attr.prompt()))
        {
            // Lời nhắc cột 2-101 được đặt ở đây theo mặc định.
            setXSSFPrompt(sheet, "", attr.prompt(), 1, 100, column, column);
        }
        // Nếu thuộc tính kết hợp được đặt, chỉ có thể chọn cột này chứ không thể nhập
        if (attr.combo().length> 0)
        {
            // Cột 2-101 được đặt ở đây theo mặc định và chỉ có thể được chọn nhưng không được nhập.
            setXSSFValidation(sheet, attr.combo(), 1, 100, column, column);
        }
    }

    /**
     * Add cell
     */
    public Cell addCell(Excel attr, Row row, T vo, Field field, int column)
    {
        Cell cell = null;
        try
        {
            // Đặt chiều cao dòng
            row.setHeight((short) (attr.height() * 20));
            // Quyết định có xuất hay không dựa trên cài đặt trong Excel. Một số trường hợp cần để trống, mong người dùng điền vào cột này.
            if (attr.isExport())
            {
                // Tạo ô
                cell = row.createCell(column);
                cell.setCellStyle(styles.get("data"));

                // Được sử dụng để đọc các thuộc tính trong đối tượng
                Object value = getTargetValue(vo, field, attr);
                String dateFormat = attr.dateFormat();
                String readConverterExp = attr.readConverterExp();
                if (StringUtils.isNotEmpty(dateFormat) && StringUtils.isNotNull(value))
                {
                    cell.setCellValue(DateUtils.parseDateToStr(dateFormat, (Date) value));
                }
                else if (StringUtils.isNotEmpty(readConverterExp) && StringUtils.isNotNull(value))
                {
                    cell.setCellValue(convertByExp(String.valueOf(value), readConverterExp));
                }
                else
                {
                    // Đặt loại cột
                    setCellVo(value, attr, cell);
                }
            }
        }
        catch (Exception e)
        {
            log.error("Failed to export Excel{}", e);
        }
        return cell;
    }

    /**
     * Đặt lời nhắc ô POI XSSFSheet
     *
     * @param Mẫu trang tính 
     * @param promptTitle tựa đề lời nhắc
     * @param prompt Nội dung lời nhắc nội dung
     * @param firstRow dòng bắt đầu
     * @param Dòng cuối  endRow
     * @param Cột bắt đầu  firstCol
     * @param Cột cuối  endCol
     */
    public void setXSSFPrompt(Sheet sheet, String promptTitle, String promptContent, int firstRow, int endRow,
            int firstCol, int endCol)
    {
        DataValidationHelper helper = sheet.getDataValidationHelper();
        DataValidationConstraint constraint = helper.createCustomConstraint("DD1");
        CellRangeAddressList regions = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
        DataValidation dataValidation = helper.createValidation(constraint, regions);
        dataValidation.createPromptBox(promptTitle, promptContent);
        dataValidation.setShowPromptBox(true);
        sheet.addValidationData(dataValidation);
    }

    /**
     * Đặt giá trị của một số cột chỉ có thể nhập dữ liệu được tạo trước, hiển thị hộp thả xuống.
     *
     * @param sheet Trang tính sẽ được thiết lập.
     * @param textlist Nội dung hiển thị trong hộp thả xuống
     * @param firstRow dòng bắt đầu
     * @param Dòng cuối  endRow
     * @param Cột bắt đầu  firstCol
     * @param Cột cuối  endCol
     * Trang tính @return.
     */
    public void setXSSFValidation(Sheet sheet, String[] textlist, int firstRow, int endRow, int firstCol, int endCol)
    {
        DataValidationHelper helper = sheet.getDataValidationHelper();
        // Tải nội dung của danh sách thả xuống
        DataValidationConstraint constraint = helper.createExplicitListConstraint(textlist);
        // Đặt ô mà tính hợp lệ của dữ liệu được tải. Bốn tham số là: hàng bắt đầu, hàng kết thúc, cột bắt đầu, cột kết thúc
        CellRangeAddressList regions = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
        // Đối tượng hợp lệ dữ liệu
        DataValidation dataValidation = helper.createValidation(constraint, regions);
        // Xử lý các vấn đề tương thích với Excel
        if (dataValidation instanceof XSSFDataValidation)
        {
            dataValidation.setSuppressDropDownArrow(true);
            dataValidation.setShowErrorBox(true);
        }
        else
        {
            dataValidation.setSuppressDropDownArrow(false);
        }

        sheet.addValidationData(dataValidation);
    }

    /**
      * Phân tích cú pháp giá trị bắt nguồn 0 = Nam, 1 = Nữ, 2 = Không xác định
      *
      * @param Giá trị tham số  propertyValue
      * @param converter
      * @return   trở lại sau khi phân tích cú pháp
      * @throws Ngoại lệ 
      */
    public static String convertByExp(String propertyValue, String converterExp) throws Exception
    {
        try
        {
            String[] convertSource = converterExp.split(",");
            for (String item : convertSource)
            {
                String[] itemArray = item.split("=");
                if (itemArray[0].equals(propertyValue))
                {
                    return itemArray[1];
                }
            }
        }
        catch (Exception e)
        {
            throw e;
        }
        return propertyValue;
    }

    /**
      * Giá trị phân tích ngược Nam = 0, Nữ = 1, Không xác định = 2
      *
      * Giá trị tham số @param propertyValue
      * @param converter
      * @Return trở lại sau khi phân tích cú pháp
      * @throws Ngoại lệ 
      */
    public static String reverseByExp(String propertyValue, String converterExp) throws Exception
    {
        try
        {
            String[] convertSource = converterExp.split(",");
            for (String item : convertSource)
            {
                String[] itemArray = item.split("=");
                if (itemArray[1].equals(propertyValue))
                {
                    return itemArray[0];
                }
            }
        }
        catch (Exception e)
        {
            throw e;
        }
        return propertyValue;
    }

    /**
     * Tên tệp được mã hóa
    */
    public String encodingFilename(String filename)
    {
        filename = UUID.randomUUID().toString() + "_" + filename + ".xlsx";
        return filename;
    }

    /**
     * Nhận đường dẫn tải xuống
     *
     * Tên tệp @param filename
     */
    public String getAbsoluteFile(String filename)
    {
        String downloadPath = Global.getDownloadPath() + filename;
        File desc = new File(downloadPath);
        if (!desc.getParentFile().exists())
        {
            desc.getParentFile().mkdirs();
        }
        return downloadPath;
    }

    /**
      * Nhận giá trị thuộc tính trong bean
      *
      * @param Đối tượng thực thể  vo
      * @param Trường 
      * @param Chú thích excel
      * @return giá trị thuộc tính cuối cùng
      * @throws Ngoại lệ 
      */
    private Object getTargetValue(T vo, Field field, Excel excel) throws Exception
    {
        Object o = field.get(vo);
        if (StringUtils.isNotEmpty(excel.targetAttr()))
        {
            String target = excel.targetAttr();
            if (target.indexOf(".") > -1)
            {
                String[] targets = target.split("[.]");
                for (String name : targets)
                {
                    o = getValue(o, name);
                }
            }
            else
            {
                o = getValue(o, target);
            }
        }
        return o;
    }

    /**
     * Nhận giá trị dưới dạng phương thức get của thuộc tính của lớp
     *
     * @param o
     * @param Tên 
     * @return Giá trị 
     * @throws Ngoại lệ 
     */
    private Object getValue(Object o, String name) throws Exception
    {
        if (StringUtils.isNotEmpty(name))
        {
            Class<?> clazz = o.getClass();
            String methodName = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
            Method method = clazz.getMethod(methodName);
            o = method.invoke(o);
        }
        return o;
    }

    /**
     * Get all defined fields
     */
    private void createExcelField()
    {
        this.fields = new ArrayList<Object[]>();
        List<Field> tempFields = new ArrayList<>();
        tempFields.addAll(Arrays.asList(clazz.getSuperclass().getDeclaredFields()));
        tempFields.addAll(Arrays.asList(clazz.getDeclaredFields()));
        for (Field field : tempFields)
        {
            //Một nốt nhạc
            if (field.isAnnotationPresent(Excel.class))
            {
                putToField(field, field.getAnnotation(Excel.class));
            }

            // Nhiều nốt nhạc
            if (field.isAnnotationPresent(Excels.class))
            {
                Excels attrs = field.getAnnotation(Excels.class);
                Excel[] excels = attrs.value();
                for (Excel excel : excels)
                {
                    putToField(field, excel);
                }
            }
        }
    }

    /**
     * Đưa vào bộ sưu tập hiện trường
     */
    private void putToField(Field field, Excel attr)
    {
        if (attr != null && (attr.type() == Type.ALL || attr.type() == type))
        {
            this.fields.add(new Object[] { field, attr });
        }
    }

    /**
     * Tạo sổ làm việc
     */
    public void createWorkbook()
    {
        this.wb = new SXSSFWorkbook(500);
    }

    /**
     * Tạo trang tính
     *
     * @param sheetKhông có số lượng sheet
     * @param Chỉ mục 
     */
    public void createSheet(double sheetNo, int index)
    {
        this.sheet = wb.createSheet();
        this.styles = createStyles(wb);
        // Set the name of the worksheet.
        if (sheetNo == 0)
        {
            wb.setSheetName(index, sheetName);
        }
        else
        {
            wb.setSheetName(index, sheetName + index);
        }
    }

    /**
     * Nhận giá trị ô
     *
     * Hàng @param
     * @param cột để lấy số cột ô
     * Giá trị ô @return
     */
    public Object getCellValue(Row row, int column)
    {
        if (row == null)
        {
            return row;
        }
        Object val = "";
        try
        {
            Cell cell = row.getCell(column);
            if (StringUtils.isNotNull(cell))
            {
                if (cell.getCellTypeEnum() == CellType.NUMERIC || cell.getCellTypeEnum() == CellType.FORMULA)
                {
                    val = cell.getNumericCellValue();
                    if (HSSFDateUtil.isCellDateFormatted(cell))
                    {
                        val = DateUtil.getJavaDate((Double) val); // POI Excel date format conversion
                    }
                    else
                    {
                        if ((Double) val % 1 > 0)
                        {
                            val = new DecimalFormat("0.00").format(val);
                        }
                        else
                        {
                            val = new DecimalFormat("0").format(val);
                        }
                    }
                }
                else if (cell.getCellTypeEnum() == CellType.STRING)
                {
                    val = cell.getStringCellValue();
                }
                else if (cell.getCellTypeEnum() == CellType.BOOLEAN)
                {
                    val = cell.getBooleanCellValue();
                }
                else if (cell.getCellTypeEnum() == CellType.ERROR)
                {
                    val = cell.getErrorCellValue();
                }

            }
        }
        catch (Exception e)
        {
            return val;
        }
        return val;
    }
}