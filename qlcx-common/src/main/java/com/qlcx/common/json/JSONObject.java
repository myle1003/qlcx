package com.qlcx.common.json;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qlcx.common.utils.StringUtils;

/**
 * Đối tượng thông báo chung, cấu trúc dữ liệu lồng nhau dựa trên Bản đồ. Hỗ trợ cấu trúc dữ liệu JSON.
 * 
 * @author qlcx
 */
public class JSONObject extends LinkedHashMap<String, Object>
{
    private static final long serialVersionUID = 1L;
    private static final Pattern arrayNamePattern = Pattern.compile("(\\w+)((\\[\\d+\\])+)");
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Cấu trúc mảng.
     */
    public static class JSONArray extends ArrayList<Object>
    {
        private static final long serialVersionUID = 1L;

        public JSONArray()
        {
            super();
        }

        public JSONArray(int size)
        {
            super(size);
        }

        @Override
        public String toString()
        {
            try
            {
                return JSON.marshal(this);
            }
            catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        }

        @Override
        public Object set(int index, Object element)
        {
            return super.set(index, transfer(element));
        }

        @Override
        public boolean add(Object element)
        {
            return super.add(transfer(element));
        }

        @Override
        public void add(int index, Object element)
        {
            super.add(index, transfer(element));
        }
    }

    public JSONObject()
    {
        super();
    }

    public JSONObject(final JSONObject other)
    {
        super(other);
    }

    @Override
    public String toString()
    {
        try
        {
            return JSON.marshal(this);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
      * Chuyển đổi thành chuỗi ở định dạng nhỏ gọn.
      *
      * @return trả về chuỗi định dạng nhỏ gọn của đối tượng này.
      */
      public String toCompactString()
      {
          try
          {
              return objectMapper.writeValueAsString(this);
          }
          catch (Exception e)
          {
              throw new RuntimeException(e);
          }
      }
 
      /**
       * Nhận giá trị nguyên của trường được chỉ định. Nếu trường không tồn tại hoặc không thể chuyển đổi thành số nguyên, giá trị null sẽ được trả về.
       *
       * @param Tên trường tên , hỗ trợ nhiều cấp độ.
       * @return trả về giá trị số nguyên được chỉ định hoặc null.
       */
    public Integer intValue(final String name)
    {
        return valueAsInt(value(name));
    }

    /**
     * Nhận giá trị nguyên của trường được chỉ định. Nếu trường không tồn tại hoặc không thể chuyển đổi thành số nguyên, giá trị defaultValue sẽ được trả về.
     *
     * @param Tên trường tên , hỗ trợ nhiều cấp độ.
     * @param defaultValue Giá trị trả về khi truy vấn không thành công.
     * @return trả về giá trị số nguyên được chỉ định hoặc defaultValue.
     */
    public Integer intValue(final String name, final Integer defaultValue)
    {
        return StringUtils.nvl(intValue(name), defaultValue);
    }

    /**
     * Nhận giá trị số nguyên dài của trường được chỉ định. Nếu trường không tồn tại hoặc không thể chuyển đổi thành số nguyên dài, giá trị null sẽ được trả về.
     *
     * @param Tên trường tên , hỗ trợ nhiều cấp độ.
     * @return trả về giá trị số nguyên dài được chỉ định hoặc null.
     */
    public Long longValue(final String name)
    {
        return valueAsLong(value(name));
    }

    /**
     * Nhận giá trị số nguyên dài của trường được chỉ định. Nếu trường không tồn tại hoặc không thể chuyển đổi thành số nguyên dài, giá trị defaultValue sẽ được trả về.
     *
     * @param Tên trường tên , hỗ trợ nhiều cấp độ.
     * @param defaultValue Giá trị trả về khi truy vấn không thành công.
     * @return trả về giá trị số nguyên dài được chỉ định hoặc defaultValue.
     */
    public Long longValue(final String name, final Long defaultValue)
    {
        return StringUtils.nvl(longValue(name), defaultValue);
    }

    /**
     * Nhận giá trị Boolean của trường được chỉ định. Nếu trường không tồn tại hoặc không thể chuyển đổi thành Boolean, giá trị null sẽ được trả về.
     *
     * @param Tên trường tên , hỗ trợ nhiều cấp độ.
     * @return trả về giá trị Boolean được chỉ định hoặc null.
     */
    public Boolean boolValue(final String name)
    {
        return valueAsBool(value(name));
    }

    /**
     * Nhận giá trị Boolean của trường được chỉ định. Nếu trường không tồn tại hoặc không thể chuyển đổi thành Boolean, giá trị defaultValue sẽ được trả về.
     *
     * @param Tên trường tên , hỗ trợ nhiều cấp độ.
     * @param defaultValue Giá trị trả về khi truy vấn không thành công.
     * @return trả về giá trị Boolean được chỉ định hoặc defaultValue.
     */
    public Boolean boolValue(final String name, final Boolean defaultValue)
    {
        return StringUtils.nvl(boolValue(name), defaultValue);
    }

    /**
     * Nhận giá trị chuỗi của trường được chỉ định. Nếu trường không tồn tại, trả về null.
     *
     * @param Tên trường tên , hỗ trợ nhiều cấp độ.
     * @return trả về giá trị chuỗi được chỉ định hoặc null.
     */
    public String strValue(final String name)
    {
        return valueAsStr(value(name));
    }

    /**
     * Nhận giá trị chuỗi của trường được chỉ định. Nếu trường không tồn tại, hãy trả về defaultValue.
    *
    * @param Tên trường tên , hỗ trợ nhiều cấp độ.
    * @param defaultValue Giá trị trả về khi truy vấn không thành công.
    * @return trả về giá trị chuỗi được chỉ định hoặc defaultValue.
    */
    public String strValue(final String name, final String defaultValue)
    {
        return StringUtils.nvl(strValue(name), defaultValue);
    }

    /**
     * Nhận giá trị của trường được chỉ định.
     *
     * @param Tên trường tên , hỗ trợ đa cấp, hỗ trợ chỉ số mảng mảng.
     * @return trả về giá trị của trường được chỉ định.
     */
    public Object value(final String name)
    {
        final int indexDot = name.indexOf('.');
        if (indexDot >= 0)
        {
            return obj(name.substring(0, indexDot)).value(name.substring(indexDot + 1));
        }
        else
        {
            final Matcher matcher = arrayNamePattern.matcher(name);
            if (matcher.find())
            {
                return endArray(matcher.group(1), matcher.group(2), new EndArrayCallback<Object>()
                {
                    @Override
                    public Object callback(JSONArray arr, int index)
                    {
                        return elementAt(arr, index);
                    }
                });
            }
            else
            {
                return get(name);
            }
        }
    }

    /**
      * Đặt giá trị của trường được chỉ định.
      *
      * @param Tên trường tên , hỗ trợ đa cấp, hỗ trợ chỉ số mảng mảng.
      * Giá trị trường giá trị @param.
      * @return trả về đối tượng này.
      */
    public JSONObject value(final String name, final Object value)
    {
        final int indexDot = name.indexOf('.');
        if (indexDot >= 0)
        {
            obj(name.substring(0, indexDot)).value(name.substring(indexDot + 1), value);
        }
        else
        {
            final Matcher matcher = arrayNamePattern.matcher(name);
            if (matcher.find())
            {
                endArray(matcher.group(1), matcher.group(2), new EndArrayCallback<Void>()
                {
                    @Override
                    public Void callback(JSONArray arr, int index)
                    {
                        elementAt(arr, index, value);
                        return null;
                    }
                });
            }
            else
            {
                set(name, value);
            }
        }
        return this;
    }

    /**
      * Lấy trường đối tượng (loại không vô hướng). Dữ liệu trả về là một cấu trúc. Khi đối tượng được chỉ định không tồn tại, một đối tượng MessageObject trống được tạo cho tên đã chỉ định.
      *
      * @param Tên trường  name. Tên nhiều cấp không được hỗ trợ và các chỉ số dưới mảng được hỗ trợ.
      * @return trả về đối tượng được chỉ định. Nếu đối tượng không tồn tại, một đối tượng MessageObject trống sẽ được tạo cho tên đã chỉ định.
      */
    public JSONObject obj(final String name)
    {
        final Matcher matcher = arrayNamePattern.matcher(name);
        if (matcher.find())
        {
            return endArray(matcher.group(1), matcher.group(2), new EndArrayCallback<JSONObject>()
            {
                @Override
                public JSONObject callback(JSONArray arr, int index)
                {
                    return objAt(arr, index);
                }
            });
        }
        else
        {
            JSONObject obj = getObj(name);
            if (obj == null)
            {
                obj = new JSONObject();
                put(name, obj);
            }
            return obj;
        }
    }

    /**
     * Nhận các trường mảng. Đối tượng tương ứng với tên được trả về dưới dạng đối tượng mảng. Khi trường được chỉ định không tồn tại, một mảng trống sẽ được tạo.
     *
     * @param Tên trường  name. Tên nhiều cấp độ và bảng chỉ dẫn không được hỗ trợ.
     * @return trả về một mảng (Danh sách).
     */
    public JSONArray arr(final String name)
    {
        JSONArray arr = getArr(name);
        if (arr == null)
        {
            arr = new JSONArray();
            put(name, arr);
        }
        return arr;
    }

    /**
     * Lấy trường đối tượng (loại không vô hướng). Dữ liệu trả về là một cấu trúc.
     *
     * @param Tên trường  name.
     * @return trả về trường đối tượng được chỉ định.
     */
    public JSONObject getObj(final String name)
    {
        return (JSONObject) get(name);
    }

    /**
     * Nhận trường kiểu mảng.
     *
     * @param Tên trường  name.
     * @return trả về trường kiểu mảng.
     */
    public JSONArray getArr(final String name)
    {
        return (JSONArray) get(name);
    }

    /**
     * Trả về giá trị nguyên của trường. Nếu nó không tồn tại, trả về null.
     *
     * @param Tên trường  name.
     * @return trả về giá trị nguyên của trường được chỉ định.
     */
    public Integer getInt(final String name)
    {
        return valueAsInt(get(name));
    }

    /**
     * Trả về giá trị nguyên của trường. Nếu nó không tồn tại, hãy trả về defaultValue.
     *
     * @param Tên trường  name.
     * Giá trị được trả về khi trường @param defaultValue không tồn tại.
     * @return trả về giá trị nguyên của trường được chỉ định.
     */
    public Integer getInt(final String name, Integer defaultValue)
    {
        return StringUtils.nvl(getInt(name), defaultValue);
    }

    /**
     * Trả về giá trị số nguyên dài của trường. Nếu nó không tồn tại, trả về null.
     *
     * @param Tên trường  name.
     * @return trả về giá trị số nguyên dài của trường được chỉ định.
     */
    public Long getLong(final String name)
    {
        return valueAsLong(get(name));
    }

    /**
     * Trả về giá trị số nguyên dài của trường. Nếu nó không tồn tại, hãy trả về defaultValue.
     *
     * @param Tên trường  name.
     * Giá trị được trả về khi trường @param defaultValue không tồn tại.
     * @return trả về giá trị số nguyên dài của trường được chỉ định.
     */
    public Long getLong(final String name, Long defaultValue)
    {
        return StringUtils.nvl(getLong(name), defaultValue);
    }

    /**
     * Trả về giá trị chuỗi trường. Nếu nó không tồn tại, trả về null.
     *
     * @param Tên trường  name.
     * @return trả về giá trị chuỗi của trường được chỉ định.
     */
    public String getStr(final String name)
    {
        return valueAsStr(get(name));
    }

    /**
     * Trả về giá trị chuỗi trường. Nếu nó không tồn tại, hãy trả về defaultValue.
     *
     * Tên trường @param name.
     * Giá trị được trả về khi trường @param defaultValue không tồn tại.
     * @return trả về giá trị chuỗi của trường được chỉ định.
     */
    public String getStr(final String name, final String defaultValue)
    {
        return StringUtils.nvl(getStr(name), defaultValue);
    }

    /**
     * Giá trị trường được trả về theo kiểu Boolean. Nếu nó không tồn tại, trả về null.
     *
     * @param Tên trường  name.
     * @return Giá trị trường .
     */
    public Boolean getBool(final String name)
    {
        return valueAsBool(get(name));
    }

    /**
     * Giá trị trường được trả về theo kiểu Boolean. Nếu nó không tồn tại, hãy trả về defaultValue.
     *
     * @param Tên trường  name.
     * Giá trị được trả về khi trường @param defaultValue không tồn tại.
     * @return Giá trị trường .
     */
    public Boolean getBool(final String name, final Boolean defaultValue)
    {
        return StringUtils.nvl(getBool(name), defaultValue);
    }

    /**
     *Đặt giá trị trường
     *
     * @param Tên trường  name
     * @param Giá trị  Giá trị trường (vô hướng: số, chuỗi, Boolean; cấu trúc: MessageObject). Nếu đó là loại Bản đồ chứ không phải loại MessageObject, nó sẽ tự động được chuyển đổi thành loại MessageObject và sau đó được lưu trữ
     * (Tại thời điểm này, nếu bạn sửa đổi dữ liệu trong Bản đồ, nó sẽ không được phản ánh trong đối tượng này).
     * @return trả về đối tượng này
     */
    public JSONObject set(final String name, final Object value)
    {
        put(name, value);
        return this;
    }

    /**
     * Chuyển đổi đối tượng này sang Java Bean.
     *
     * @param beanClass Đối tượng lớp Java Bean.
     * @return trả về Java Bean đã chuyển đổi.
     */
    public <T> T asBean(Class<T> beanClass)
    {
        try
        {
            return JSON.unmarshal(JSON.marshal(this), beanClass);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Ghi đè phương thức lớp cơ sở. Nếu giá trị thuộc loại Bản đồ, nhưng không thuộc loại MessageObject, hãy tạo một MessageObject chứa nội dung tương đương với giá trị của Bản đồ gốc (Lưu ý: Nếu bạn thay đổi nội dung của bản đồ sau đó, nó sẽ không được phản ánh trong
     * Trong MessageObject). Mục đích của việc nạp chồng phương thức này là để cho phép JSON được phân tích cú pháp chính xác thành các đối tượng MessageObject. Không nên gọi phương thức này trực tiếp, vui lòng sử dụng phương thức set (tên, giá trị) để đặt giá trị trường.
     */
    @Override
    public Object put(String key, Object value)
    {
        return super.put(key, transfer(value));
    }

    public static Integer valueAsInt(Object value)
    {
        if (value instanceof Integer)
        {
            return (Integer) value;
        }
        else if (value instanceof Number)
        {
            return ((Number) value).intValue();
        }
        else if (value instanceof String)
        {
            return Integer.valueOf((String) value);
        }
        else if (value instanceof Boolean)
        {
            return ((Boolean) value) ? 1 : 0;
        }
        else
        {
            return null;
        }
    }

    public static Long valueAsLong(Object value)
    {
        if (value instanceof Long)
        {
            return (Long) value;
        }
        else if (value instanceof Number)
        {
            return ((Number) value).longValue();
        }
        else if (value instanceof String)
        {
            return Long.valueOf((String) value);
        }
        else if (value instanceof Boolean)
        {
            return ((Boolean) value) ? 1L : 0L;
        }
        else
        {
            return null;
        }
    }

    public static String valueAsStr(Object value)
    {
        if (value instanceof String)
        {
            return (String) value;
        }
        else if (value != null)
        {
            return value.toString();
        }
        else
        {
            return null;
        }
    }

    public static Boolean valueAsBool(Object value)
    {
        if (value instanceof Boolean)
        {
            return (Boolean) value;
        }
        else if (value instanceof Number)
        {
            return ((Number) value).doubleValue() != 0.0;
        }
        else if (value instanceof String)
        {
            return Boolean.valueOf((String) value);
        }
        else
        {
            return null;
        }
    }

    /**
      * Chuyển đổi tất cả các kiểu là kiểu Bản đồ nhưng không phải là MessageObject ở tất cả các cấp thành kiểu MessageObject.
      *
      * @param Giá trị .
      * @return trả về giá trị đã chuyển đổi.
      */
    @SuppressWarnings("unchecked")
    private static Object transfer(final Object value)
    {
        if (!(value instanceof JSONObject) && value instanceof Map)
        {
            return toObj((Map<String, Object>) value);
        }
        else if (!(value instanceof JSONArray) && value instanceof Collection)
        {
            return toArr((Collection<Object>) value);
        }
        else
        {
            return value;
        }
    }

    private static JSONArray toArr(final Collection<Object> list)
    {
        final JSONArray arr = new JSONArray(list.size());
        for (final Object element : list)
        {
            arr.add(element);
        }
        return arr;
    }

    private static JSONObject toObj(final Map<String, Object> map)
    {
        final JSONObject obj = new JSONObject();
        for (final Map.Entry<String, Object> ent : map.entrySet())
        {
            obj.put(ent.getKey(), transfer(ent.getValue()));
        }
        return obj;
    }

    /**
     * Trả về phần tử chỉ số con được chỉ định dưới dạng một mảng. Nếu nó không tồn tại, hãy tạo một mảng trống tại vị trí đó.
    *
    * @param arr Mảng hiện tại.
    * @param Chỉ số dưới chỉ mục .
    * @return trả về phần tử của chỉ số con được chỉ định của mảng hiện tại, phải là một mảng.
    */
    private static JSONArray arrayAt(JSONArray arr, int index)
    {
        expand(arr, index);
        if (arr.get(index) == null)
        {
            arr.set(index, new JSONArray());
        }
        return (JSONArray) arr.get(index);
    }

    /**
     * Trả về phần tử chỉ số con được chỉ định dưới dạng cấu trúc. Nếu nó không tồn tại, hãy tạo một cấu trúc trống tại vị trí đó.
     *
     * @param arr Mảng hiện tại.
     * @param Chỉ số dưới chỉ mục .
     * @return trả về phần tử chỉ số con được chỉ định của mảng hiện tại, là một cấu trúc.
     */
    private static JSONObject objAt(final JSONArray arr, int index)
    {
        expand(arr, index);
        if (arr.get(index) == null)
        {
            arr.set(index, new JSONObject());
        }
        return (JSONObject) arr.get(index);
    }

    /**
     *Đặt giá trị của vị trí chỉ số con được chỉ định trong mảng.
     *
     * @param arr mảng.
     * @param Chỉ số dưới chỉ mục .
     * @param Giá trị .
     */
    private static void elementAt(final JSONArray arr, final int index, final Object value)
    {
        expand(arr, index).set(index, value);
    }

    /**
     * Nhận giá trị của phần tử chỉ số được chỉ định của mảng.
     *
     * @param arr mảng.
     * @param Chỉ số dưới chỉ mục .
     * Giá trị @ trở lại.
     */
    private static Object elementAt(final JSONArray arr, final int index)
    {
        return expand(arr, index).get(index);
    }

    /**
     * Mở rộng mảng đến chỉ số con được chỉ định để ngăn chỉ số con vượt qua ranh giới trong quá trình truy cập.
     *
     * @param arr mảng
     * @param Chỉ số dưới chỉ mục 
     * @return trả về mảng đến
     */
    private static JSONArray expand(final JSONArray arr, final int index)
    {
        while (arr.size() <= index)
        {
            arr.add(null);
        }
        return arr;
    }

    /**
     * Gọi lại mảng cuối cùng.
     *
     * @author Mike
     *
     * @param <T> kiểu dữ liệu trả về gọi lại.
     */
    private interface EndArrayCallback<T>
    {
        /**
         * Phương thức này sẽ được gọi khi định vị mảng cấp cuối cùng.
         *
         * @param arr Đối tượng mảng cuối cùng.
         * @param Chỉ mục  Cấp cuối cùng của chỉ mục.
         * @return trả về giá trị trả về của lệnh gọi lại.
         */
        T callback(JSONArray arr, int index);
    }

    /**
     * Các chức năng công cụ để xử lý mảng nhiều chiều (kể cả mảng một chiều). Tên của mảng đa chiều là: array [1] [2] [3], then name = array, indexStr = [1] [2] [3], trong lệnh gọi lại, endArr sẽ là
     * Đối tượng được chỉ định bởi mảng [1] [2], indexe = 3.
     *
     * @param Tên không có chỉ số phụ không hỗ trợ tên nhiều cấp.
     * @param indexesStr Phần chỉ mục của chuỗi, chẳng hạn như: [1] [2] [3]
     * Chức năng gọi lại callback @param.
     * @return trả về giá trị trả về của hàm gọi lại.
     */
    private <T> T endArray(final String name, final String indexesStr, final EndArrayCallback<T> callback)
    {
        JSONArray endArr = arr(name);
        final int[] indexes = parseIndexes(indexesStr);
        int i = 0;
        while (i < indexes.length - 1)
        {
            endArr = arrayAt(endArr, indexes[i++]);
        }
        return callback.callback(endArr, indexes[i]);
    }

    private static int[] parseIndexes(final String s)
    {
        int[] indexes = null;
        List<Integer> list = new ArrayList<Integer>();

        final StringTokenizer st = new StringTokenizer(s, "[]");
        while (st.hasMoreTokens())
        {
            final int index = Integer.valueOf(st.nextToken());
            if (index < 0)
            {
                throw new RuntimeException(String.format("Illegal index %1$d in \"%2$s\"", index, s));
            }

            list.add(index);
        }

        indexes = new int[list.size()];
        int i = 0;
        for (Integer tmp : list.toArray(new Integer[list.size()]))
        {
            indexes[i++] = tmp;
        }

        return indexes;
    }
}
