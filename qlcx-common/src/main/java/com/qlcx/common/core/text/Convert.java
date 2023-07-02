package com.qlcx.common.core.text;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.text.NumberFormat;
import java.util.Set;

import com.qlcx.common.utils.StringUtils;

/**
 * Công cụ chuyển đổi loại
 */
public class Convert
{
    /**
     * Chuyển đổi thành chuỗi <br>
     * Nếu giá trị đã cho là null hoặc chuyển đổi không thành công, hãy trả về giá trị mặc định <br>
     * Chuyển đổi không thành công mà không có lỗi
     *
     * @param giá trị Giá trị được chuyển đổi
     * @param defaultValue Giá trị mặc định khi lỗi chuyển đổi
     * @return kết quả
     */
    public static String toStr(Object value, String defaultValue)
    {
        if (null == value)
        {
            return defaultValue;
        }
        if (value instanceof String)
        {
            return (String) value;
        }
        return value.toString();
    }

    /**
     * Chuyển đổi thành chuỗi <br>
     * Nếu giá trị đã cho là <code> null </code> hoặc chuyển đổi không thành công, hãy trả về giá trị mặc định <code> null </code> <br>
     * Chuyển đổi không thành công mà không có lỗi
     *
     * @param giá trị Giá trị được chuyển đổi
     * @return kết quả
     */
    public static String toStr(Object value)
    {
        return toStr(value, null);
    }

    /**
     * Chuyển thành ký tự <br>
     * Nếu giá trị đã cho là null hoặc chuyển đổi không thành công, hãy trả về giá trị mặc định <br>
     * Chuyển đổi không thành công mà không có lỗi
     *
     * @param giá trị Giá trị được chuyển đổi
     * @param defaultValue Giá trị mặc định khi lỗi chuyển đổi
     * @return kết quả
     */
    public static Character toChar(Object value, Character defaultValue)
    {
        if (null == value)
        {
            return defaultValue;
        }
        if (value instanceof Character)
        {
            return (Character) value;
        }

        final String valueStr = toStr(value, null);
        return StringUtils.isEmpty(valueStr)? defaultValue: valueStr.charAt(0);
    }

    /**
     * Chuyển thành ký tự <br>
     * Nếu giá trị đã cho là <code> null </code> hoặc chuyển đổi không thành công, hãy trả về giá trị mặc định <code> null </code> <br>
     * Chuyển đổi không thành công mà không có lỗi
     *
     * @param giá trị Giá trị được chuyển đổi
     * @return kết quả
     */
    public static Character toChar(Object value)
    {
        return toChar(value, null);
    }

    /**
     * Chuyển thành byte <br>
     * Nếu giá trị đã cho là <code> null </code> hoặc chuyển đổi không thành công, hãy trả về giá trị mặc định <br>
     * Chuyển đổi không thành công mà không có lỗi
     *
     * @param giá trị Giá trị được chuyển đổi
     * @param Giá trị mặc định Giá trị mặc định khi lỗi chuyển đổi
     * @return Kết qua
     */
    public static Byte toByte(Object value, Byte defaultValue)
    {
        if (value == null)
        {
            return defaultValue;
        }
        if (value instanceof Byte)
        {
            return (Byte) value;
        }
        if (value instanceof Number)
        {
            return ((Number) value).byteValue();
        }
        final String valueStr = toStr(value, null);
        if (StringUtils.isEmpty(valueStr))
        {
            return defaultValue;
        }
        try
        {
            return Byte.parseByte(valueStr);
        }
        catch (Exception e)
        {
            return defaultValue;
        }
    }

    /**
     *Chuyển thành byte <br>
     * Nếu giá trị đã cho là <code> null </code> hoặc chuyển đổi không thành công, hãy trả về giá trị mặc định <code> null </code> <br>
     * Chuyển đổi không thành công mà không có lỗi
     *
     * @param giá trị Giá trị được chuyển đổi
     * @return kết quả
     */
    public static Byte toByte(Object value)
    {
        return toByte(value, null);
    }

    /**
     * Chuyển đổi sang Ngắn hạn <br>
     * Nếu giá trị đã cho là <code> null </code> hoặc chuyển đổi không thành công, hãy trả về giá trị mặc định <br>
     * Chuyển đổi không thành công mà không có lỗi
     *
     * @param giá trị Giá trị được chuyển đổi
     * @param defaultValue Giá trị mặc định khi lỗi chuyển đổi
     * @return Kết quả
     */
    public static Short toShort(Object value, Short defaultValue)
    {
        if (value == null)
        {
            return defaultValue;
        }
        if (value instanceof Short)
        {
            return (Short) value;
        }
        if (value instanceof Number)
        {
            return ((Number) value).shortValue();
        }
        final String valueStr = toStr(value, null);
        if (StringUtils.isEmpty(valueStr))
        {
            return defaultValue;
        }
        try
        {
            return Short.parseShort(valueStr.trim());
        }
        catch (Exception e)
        {
            return defaultValue;
        }
    }

    /**
     * Chuyển đổi sang Ngắn hạn <br>
     * Nếu giá trị đã cho là <code> null </code> hoặc chuyển đổi không thành công, hãy trả về giá trị mặc định <code> null </code> <br>
     * Chuyển đổi không thành công mà không có lỗi
     *
     * @param giá trị Giá trị được chuyển đổi
     * @return Kết quả
     */
    public static Short toShort(Object value)
    {
        return toShort(value, null);
    }

    /**
     * Chuyển đổi thành Số <br>
     * Nếu giá trị đã cho trống hoặc chuyển đổi không thành công, hãy trả về giá trị mặc định <br>
     * Chuyển đổi không thành công mà không có lỗi
     *
     * @param giá trị Giá trị được chuyển đổi
     * @param defaultValue Giá trị mặc định khi lỗi chuyển đổi
     * @return Kết quả
     */
    public static Number toNumber(Object value, Number defaultValue)
    {
        if (value == null)
        {
            return defaultValue;
        }
        if (value instanceof Number)
        {
            return (Number) value;
        }
        final String valueStr = toStr(value, null);
        if (StringUtils.isEmpty(valueStr))
        {
            return defaultValue;
        }
        try
        {
            return NumberFormat.getInstance().parse(valueStr);
        }
        catch (Exception e)
        {
            return defaultValue;
        }
    }

    /**
     * Chuyển đổi thành Số <br>
     * Nếu giá trị đã cho trống hoặc chuyển đổi không thành công, hãy trả về giá trị mặc định <code> null </code> <br>
     * Chuyển đổi không thành công mà không có lỗi
     *
     * @param giá trị Giá trị được chuyển đổi
     * @return Kết quả
     */
    public static Number toNumber(Object value)
    {
        return toNumber(value, null);
    }

    /**
      * Chuyển thành int <br>
      * Nếu giá trị đã cho trống hoặc chuyển đổi không thành công, hãy trả về giá trị mặc định <br>
      * Chuyển đổi không thành công mà không có lỗi
      *
      * @param giá trị Giá trị được chuyển đổi
      * @param defaultValue Giá trị mặc định khi lỗi chuyển đổi
      * @return kết quả
      */
    public static Integer toInt(Object value, Integer defaultValue)
    {
        if (value == null)
        {
            return defaultValue;
        }
        if (value instanceof Integer)
        {
            return (Integer) value;
        }
        if (value instanceof Number)
        {
            return ((Number) value).intValue();
        }
        final String valueStr = toStr(value, null);
        if (StringUtils.isEmpty(valueStr))
        {
            return defaultValue;
        }
        try
        {
            return Integer.parseInt(valueStr.trim());
        }
        catch (Exception e)
        {
            return defaultValue;
        }
    }

    /**
      * Chuyển thành int <br>
      * Nếu giá trị đã cho là <code> null </code> hoặc chuyển đổi không thành công, hãy trả về giá trị mặc định <code> null </code> <br>
      * Chuyển đổi không thành công mà không có lỗi
      *
      * @param Giá trị được chuyển đổi
      * @return kết quả
      */
    public static Integer toInt(Object value)
    {
        return toInt(value, null);
    }

    /**
     * Chuyển sang mảng Số nguyên <br>
    *
    * @param str giá trị được chuyển đổi
    * @return kết quả
    */
    public static Integer[] toIntArray(String str)
    {
        return toIntArray(",", str);
    }

    /**
     * Chuyển sang mảng dài <br>
     *
     * @param str giá trị được chuyển đổi
     * @return kết quả
     */
    public static Long[] toLongArray(String str)
    {
        return toLongArray(",", str);
    }

    /**
     * Chuyển sang mảng Số nguyên <br>
     *
     * @param Dấu phân tách 
     * @param chia nhỏ giá trị được chuyển đổi
     * @return kết quả
     */
    public static Integer[] toIntArray(String split, String str)
    {
        if (StringUtils.isEmpty(str))
        {
            return new Integer[] {};
        }
        String[] arr = str.split(split);
        final Integer[] ints = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++)
        {
            final Integer v = toInt(arr[i], 0);
            ints[i] = v;
        }
        return ints;
    }

    /**
     * Chuyển sang mảng dài <br>
     *
     * @param Dấu phân tách 
     * @param str giá trị được chuyển đổi
     * @return kết quả
     */
    public static Long[] toLongArray(String split, String str)
    {
        if (StringUtils.isEmpty(str))
        {
            return new Long[] {};
        }
        String[] arr = str.split(split);
        final Long[] longs = new Long[arr.length];
        for (int i = 0; i <arr.length; i++)
        {
            final Long v = toLong(arr[i], null);
            longs[i] = v;
        }
        return longs;
    }

    /**
     * Chuyển đổi sang mảng chuỗi <br>
     *
     * @param str giá trị được chuyển đổi
     * @return kết quả
     */
    public static String[] toStrArray(String str)
    {
        return toStrArray(",", str);
    }
    /**
     * Chuyển đổi sang mảng chuỗi <br>
     *
     * @param dấu phân tách
     * @param chia nhỏ giá trị đã chuyển đổi
     * @return kết quả
     */
    public static String[] toStrArray(String split, String str)
    {
        return str.split(split);
    }

    /**
     * Chuyển thành dài <br>
     * Nếu giá trị đã cho trống hoặc chuyển đổi không thành công, hãy trả về giá trị mặc định <br>
     * Chuyển đổi không thành công mà không có lỗi
     *
     * @param Giá trị được chuyển đổi
     * @param defaultValue Giá trị mặc định khi lỗi chuyển đổi
     * @return kết quả
     */
    public static Long toLong(Object value, Long defaultValue)
    {
        if (value == null)
        {
            return defaultValue;
        }
        if (value instanceof Long)
        {
            return (Long) value;
        }
        if (value instanceof Number)
        {
            return ((Number) value).longValue();
        }
        final String valueStr = toStr(value, null);
        if (StringUtils.isEmpty(valueStr))
        {
            return defaultValue;
        }
        try
        {
            // Hỗ trợ ký hiệu khoa học
            return new BigDecimal(valueStr.trim()).longValue();
        }
        catch (Exception e)
        {
            return defaultValue;
        }
    }

    /**
     * Chuyển thành dài <br>
    * Nếu giá trị đã cho là <code> null </code> hoặc chuyển đổi không thành công, hãy trả về giá trị mặc định <code> null </code> <br>
    * Chuyển đổi không thành công mà không có lỗi
    *
    * @param Giá trị được chuyển đổi
    * @return kết quả
    */
    public static Long toLong(Object value)
    {
        return toLong(value, null);
    }

    /**
     * Chuyển đổi thành đôi <br>
     * Nếu giá trị đã cho trống hoặc chuyển đổi không thành công, hãy trả về giá trị mặc định <br>
     * Chuyển đổi không thành công mà không có lỗi
     *
     * @param Giá trị được chuyển đổi
     * @param defaultValue Giá trị mặc định khi lỗi chuyển đổi
     * @return kết quả
     */
    public static Double toDouble(Object value, Double defaultValue)
    {
        if (value == null)
        {
            return defaultValue;
        }
        if (value instanceof Double)
        {
            return (Double) value;
        }
        if (value instanceof Number)
        {
            return ((Number) value).doubleValue();
        }
        final String valueStr = toStr(value, null);
        if (StringUtils.isEmpty(valueStr))
        {
            return defaultValue;
        }
        try
        {
        	// Hỗ trợ ký hiệu khoa học
            return new BigDecimal(valueStr.trim()).doubleValue();
        }
        catch (Exception e)
        {
            return defaultValue;
        }
    }

    /**
     * Chuyển đổi thành đôi <br>
     * Nếu giá trị đã cho trống hoặc chuyển đổi không thành công, hãy trả về giá trị mặc định <code> null </code> <br>
     * Chuyển đổi không thành công mà không có lỗi
     *
     * @param Giá trị được chuyển đổi
     * @return kết quả
     */
    public static Double toDouble(Object value)
    {
        return toDouble(value, null);
    }

    /**
     * Chuyển đổi sang Float <br>
     * Nếu giá trị đã cho trống hoặc chuyển đổi không thành công, hãy trả về giá trị mặc định <br>
     * Chuyển đổi không thành công mà không có lỗi
     *
     * @param giá trị Giá trị được chuyển đổi
     * @param defaultValue Giá trị mặc định khi lỗi chuyển đổi
     * @return kết quả
     */
    public static Float toFloat(Object value, Float defaultValue)
    {
        if (value == null)
        {
            return defaultValue;
        }
        if (value instanceof Float)
        {
            return (Float) value;
        }
        if (value instanceof Number)
        {
            return ((Number) value).floatValue();
        }
        final String valueStr = toStr(value, null);
        if (StringUtils.isEmpty(valueStr))
        {
            return defaultValue;
        }
        try
        {
            return Float.parseFloat(valueStr.trim());
        }
        catch (Exception e)
        {
            return defaultValue;
        }
    }

    /**
     * Chuyển đổi sang Float <br>
    * Nếu giá trị đã cho trống hoặc chuyển đổi không thành công, hãy trả về giá trị mặc định <code> null </code> <br>
    * Chuyển đổi không thành công mà không có lỗi
    *
    * Giá trị @param Giá trị được chuyển đổi
    * @return kết quả
    */
    public static Float toFloat(Object value)
    {
        return toFloat(value, null);
    }

    /**
     * Chuyển đổi sang boolean <br>
     * Các giá trị chuỗi được hỗ trợ là: true, false, yes, ok, no, 1,0 Nếu giá trị đã cho trống hoặc chuyển đổi không thành công, hãy trả về giá trị mặc định <br>
     * Chuyển đổi không thành công mà không có lỗi
     *
     * @param Giá trị được chuyển đổi
     * @param defaultValue Giá trị mặc định khi lỗi chuyển đổi
     * @return kết quả
     */
    public static Boolean toBool(Object value, Boolean defaultValue)
    {
        if (value == null)
        {
            return defaultValue;
        }
        if (value instanceof Boolean)
        {
            return (Boolean) value;
        }
        String valueStr = toStr(value, null);
        if (StringUtils.isEmpty(valueStr))
        {
            return defaultValue;
        }
        valueStr = valueStr.trim().toLowerCase();
        switch (valueStr)
        {
            case "true":
                return true;
            case "false":
                return false;
            case "yes":
                return true;
            case "ok":
                return true;
            case "no":
                return false;
            case "1":
                return true;
            case "0":
                return false;
            default:
                return defaultValue;
        }
    }

    /**
     *Chuyển đổi sang boolean <br>
    * Nếu giá trị đã cho trống hoặc chuyển đổi không thành công, hãy trả về giá trị mặc định <code> null </code> <br>
    * Chuyển đổi không thành công mà không có lỗi
    *
    * @param Giá trị được chuyển đổi
    * @return kết quả
    * /
    public static Boolean toBool(Object value)
    {
        return toBool(value, null);
    }

    /**
     * Chuyển đổi thành đối tượng Enum <br>
     * Nếu giá trị đã cho trống hoặc chuyển đổi không thành công, hãy trả về giá trị mặc định <br>
     *
     * @param clazz Enum's Class
     * @param Giá trị 
     * @param Giá trị mặc định  defaultValue
     * @return Enum
     */
    public static <E extends Enum<E>> E toEnum(Class<E> clazz, Object value, E defaultValue)
    {
        if (value == null)
        {
            return defaultValue;
        }
        if (clazz.isAssignableFrom(value.getClass()))
        {
            @SuppressWarnings("unchecked")
            E myE = (E) value;
            return myE;
        }
        final String valueStr = toStr(value, null);
        if (StringUtils.isEmpty(valueStr))
        {
            return defaultValue;
        }
        try
        {
            return Enum.valueOf(clazz, valueStr);
        }
        catch (Exception e)
        {
            return defaultValue;
        }
    }

    /**
     * Chuyển đổi thành đối tượng Enum <br>
    * Nếu giá trị đã cho trống hoặc chuyển đổi không thành công, hãy trả về giá trị mặc định <code> null </code> <br>
    *
    * @param clazz Enum's Class
    * @param value
    * @return Enum
    */
    public static <E extends Enum<E>> E toEnum(Class<E> clazz, Object value)
    {
        return toEnum(clazz, value, null);
    }

    /**
     * Chuyển đổi sang BigInteger <br>
     * Nếu giá trị đã cho trống hoặc chuyển đổi không thành công, hãy trả về giá trị mặc định <br>
     * Chuyển đổi không thành công mà không có lỗi
     *
     * @param Giá trị được chuyển đổi
     * @param defaultValue Giá trị mặc định khi lỗi chuyển đổi
     * @return kết quả
     */
    public static BigInteger toBigInteger(Object value, BigInteger defaultValue)
    {
        if (value == null)
        {
            return defaultValue;
        }
        if (value instanceof BigInteger)
        {
            return (BigInteger) value;
        }
        if (value instanceof Long)
        {
            return BigInteger.valueOf((Long) value);
        }
        final String valueStr = toStr(value, null);
        if (StringUtils.isEmpty(valueStr))
        {
            return defaultValue;
        }
        try
        {
            return new BigInteger(valueStr);
        }
        catch (Exception e)
        {
            return defaultValue;
        }
    }

    /**
    * Chuyển đổi sang BigInteger <br>
     * Nếu giá trị đã cho trống hoặc chuyển đổi không thành công, hãy trả về giá trị mặc định <br>
     * Chuyển đổi không thành công mà không có lỗi
     *
     * @param Giá trị được chuyển đổi
     * @param defaultValue Giá trị mặc định khi lỗi chuyển đổi
     * @return kết quả
    */
    public static BigInteger toBigInteger(Object value)
    {
        return toBigInteger(value, null);
    }

    /**
     *Chuyển đổi sang BigDecimal <br>
     * Nếu giá trị đã cho trống hoặc chuyển đổi không thành công, hãy trả về giá trị mặc định <br>
     * Chuyển đổi không thành công mà không có lỗi
     *
     * @param Giá trị được chuyển đổi
     * @param defaultValue Giá trị mặc định khi lỗi chuyển đổi
     * @return kết quả
     */
    public static BigDecimal toBigDecimal(Object value, BigDecimal defaultValue)
    {
        if (value == null)
        {
            return defaultValue;
        }
        if (value instanceof BigDecimal)
        {
            return (BigDecimal) value;
        }
        if (value instanceof Long)
        {
            return new BigDecimal((Long) value);
        }
        if (value instanceof Double)
        {
            return new BigDecimal((Double) value);
        }
        if (value instanceof Integer)
        {
            return new BigDecimal((Integer) value);
        }
        final String valueStr = toStr(value, null);
        if (StringUtils.isEmpty(valueStr))
        {
            return defaultValue;
        }
        try
        {
            return new BigDecimal(valueStr);
        }
        catch (Exception e)
        {
            return defaultValue;
        }
    }

    /**
     * Chuyển đổi sang BigDecimal <br>
     * Nếu giá trị đã cho trống hoặc chuyển đổi không thành công, hãy trả về giá trị mặc định <br>
     * Chuyển đổi không thành công mà không có lỗi
     *
     * @param Giá trị được chuyển đổi
     * @param defaultValue Giá trị mặc định khi lỗi chuyển đổi
     * @return kết quả
     */
    public static BigDecimal toBigDecimal(Object value)
    {
        return toBigDecimal(value, null);
    }

    /**
     * Chuyển đối tượng thành chuỗi <br>
     * 1. Mảng Byte và ByteBuffer sẽ được chuyển đổi thành một mảng các chuỗi tương ứng 2. Mảng đối tượng sẽ gọi phương thức Arrays.toString
     *
     * @param Đối tượng  obj
     * @return Chuỗi 
     */
    public static String utf8Str(Object obj)
    {
        return str(obj, CharsetKit.CHARSET_UTF_8);
    }

    /**
     * Chuyển đối tượng thành chuỗi <br>
     * 1. Mảng Byte và ByteBuffer sẽ được chuyển đổi thành một mảng các chuỗi tương ứng 2. Mảng đối tượng sẽ gọi phương thức Arrays.toString
     *
     * @param Đối tượng  obj
     * @param Bộ ký tự charsetName
     * @return Chuỗi
     */
    public static String str(Object obj, String charsetName)
    {
        return str(obj, Charset.forName(charsetName));
    }

    /**
     * Chuyển đối tượng thành chuỗi <br>
     * 1. Mảng Byte và ByteBuffer sẽ được chuyển đổi thành một mảng các chuỗi tương ứng 2. Mảng đối tượng sẽ gọi phương thức Arrays.toString
     *
     * @param Đối tượng  obj
     * @param Bộ ký tự  
     * @return Chuỗi
     */
    public static String str(Object obj, Charset charset)
    {
        if (null == obj)
        {
            return null;
        }

        if (obj instanceof String)
        {
            return (String) obj;
        }
        else if (obj instanceof byte[] || obj instanceof Byte[])
        {
            return str((Byte[]) obj, charset);
        }
        else if (obj instanceof ByteBuffer)
        {
            return str((ByteBuffer) obj, charset);
        }
        return obj.toString();
    }

    /**
    * Chuyển đổi mảng byte thành chuỗi
    *
    * @param mảng byte  
    * @param Bộ ký tự 
    * @return Chuỗi
    */
    public static String str(byte[] bytes, String charset)
    {
        return str(bytes, StringUtils.isEmpty(charset)? Charset.defaultCharset(): Charset.forName(charset));
    }

    /**
     * Giải mã bytecode
     *
     * Chuỗi dữ liệu @param
     * Bộ ký tự ký tự @param, nếu trường này trống, kết quả giải mã phụ thuộc vào nền tảng
     * Chuỗi được giải mã @return
     */
    public static String str(byte[] data, Charset charset)
    {
        if (data == null)
        {
            return null;
        }

        if (null == charset)
        {
            return new String(data);
        }
        return new String(data, charset);
    }

    /**
     * Chuyển đổi dữ liệu byteBuffer được mã hóa thành chuỗi
     *
     * @param Dữ liệu 
     * @param Bộ ký tự ký tự, nếu nó trống, hãy sử dụng bộ ký tự hệ thống hiện tại
     * @return Chuỗi 
     */
    public static String str(ByteBuffer data, String charset)
    {
        if (data == null)
        {
            return null;
        }

        return str(data, Charset.forName(charset));
    }

    /**
     * Chuyển đổi dữ liệu byteBuffer được mã hóa thành chuỗi
     *
     * @param Dữ liệu 
     * @param Bộ ký tự, nếu nó trống, hãy sử dụng bộ ký tự hệ thống hiện tại
     * @return Chuỗi
     */
    public static String str(ByteBuffer data, Charset charset)
    {
        if (null == charset)
        {
            charset = Charset.defaultCharset();
        }
        return charset.decode(data).toString();
    }

    // ------------------------------------------------ ----------------------- Full-width half-width conversion
    /**
     * Nửa góc đến góc đầy đủ
     *
     * @param Chuỗi đầu vào .
     * @return chuỗi full-width.
     */
    public static String toSBC(String input)
    {
        return toSBC(input, null);
    }

    /**
     * Nửa góc đến góc đầy đủ
     *
     * @param Chuỗi đầu vào .
     * @return chuỗi full-width.
     */
    public static String toSBC(String input, Set<Character> notConvertSet)
    {
        char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++)
        {
            if (null != notConvertSet && notConvertSet.contains(c[i]))
            {
                // Skip unreplaced characters
                continue;
            }

            if (c[i] == ' ')
            {
                c[i] = '\u3000';
            }
            else if (c[i] < '\177')
            {
                c[i] = (char) (c[i] + 65248);

            }
        }
        return new String(c);
    }

    /**
     * Góc đầy đủ đến một nửa góc
    *
    * @param Chuỗi đầu vào .
    * @return chuỗi nửa chiều rộng
    */
    public static String toDBC(String input)
    {
        return toDBC(input, null);
    }

    /**
     * Thay toàn bộ chiều rộng thành nửa chiều rộng
     *
     * @param Văn bản 
     * @param notConvertSet không thay thế bộ ký tự
     * @ quay lại ký tự được thay thế
     */
    public static String toDBC(String text, Set<Character> notConvertSet)
    {
        char c[] = text.toCharArray();
        for (int i = 0; i <c.length; i++)
        {
            if (null != notConvertSet && notConvertSet.contains(c[i]))
            {
                // Skip unreplaced characters
                continue;
            }

            if (c[i] == '\u3000')
            {
                c[i] = ' ';
            }
            else if (c[i] > '\uFF00' && c[i] < '\uFF5F')
            {
                c[i] = (char) (c[i] - 65248);
            }
        }
        String returnString = new String(c);

        return returnString;
    }

    /**
      * Chuyển đổi vốn của số tiền kỹ thuật số, viết hoàn chỉnh đầu tiên, sau đó thay thế các số không bằng các số không
      *
      * @param n số
      * @return số viết hoa tiếng Trung
      */
    public static String digitUppercase(double n)
    {
        String[] fraction = { "Angle", "Minute" };
        String[] digit = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
        String[][] unit = { { "Yuan", "Thounsands", "Billion" }, { "", "Pickup", "Bai", "Thousand" } };

        String head = n < 0 ? "Negative" : "";
        n = Math.abs(n);

        String s = "";
        for (int i = 0; i < fraction.length; i++)
        {
            s += (digit[(int) (Math.floor(n * 10 * Math.pow(10, i)) % 10)] + fraction[i]).replaceAll("(0.)+'", "");
        }
        if (s.length() < 1)
        {
            s = "Whole";
        }
        int integerPart = (int) Math.floor(n);

        for (int i = 0; i < unit[0].length && integerPart > 0; i++)
        {
            String p = "";
            for (int j = 0; j < unit[1].length && n > 0; j++)
            {
                p = digit[integerPart % 10] + unit[1][j] + p;
                integerPart = integerPart / 10;
            }
            s = p.replaceAll("(0.)*0$", "").replaceAll("^$", "0") + unit[0][i] + s;
        }
        return head + s.replaceAll("(0.)*0 Yuan", "Yuan").replaceFirst("(0.)+", "").replaceAll("(0.)+", "0").replaceAll("^Whole$", "0 Yaun");
    }
}
