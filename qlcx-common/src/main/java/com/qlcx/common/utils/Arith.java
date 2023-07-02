package com.qlcx.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Số học dấu phẩy động chính xác
 */
public class Arith
{

    /** Độ chính xác phân chia mặc định*/
    private static final int DEF_DIV_SCALE = 10;

    /** Không thể khởi tạo lớp này */
    private Arith()
    {
    }

    /**
     * Cung cấp hoạt động cộng chính xác.
     * @param v1 addend
     * @param v2 addend
     * @ trả về tổng của hai tham số
     */
    public static double add(double v1, double v2)
    {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    /**
     * Cung cấp phép toán trừ chính xác.
     * @param v1 bị trừ
     * @param v2 trừ
     * @return Sự khác biệt của hai tham số
     */
    public static double sub(double v1, double v2)
    {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * Cung cấp phép nhân chính xác.
     * @param v1 nhiều lần
     * @param Hệ số  v2
     * @return sản phẩm của hai tham số
     */
    public static double mul(double v1, double v2)
    {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * Cung cấp (tương đối) hoạt động phân chia chính xác, khi xảy ra tình huống phân chia vô tận, chính xác
     * 10 chữ số sau dấu thập phân, chữ số tiếp theo được làm tròn.
     * @param v1 cổ tức
     * @param Số chia  v2
     * @return thương số của hai tham số
     */
    public static double div(double v1, double v2)
    {
        return div(v1, v2, DEF_DIV_SCALE);
    }

    /**
     * Cung cấp (tương đối) chính xác phép toán phân chia. Khi một tình huống không thể cạn kiệt xảy ra, thông số thang đo cho biết
     * Cố định độ chính xác, làm tròn số liệu.
     * @param v1 cổ tức
     * @param Số chia  v2
     * @param Thang đo  chỉ ra rằng nó cần chính xác đến vài chữ số thập phân.
     * @return thương số của hai tham số
     */
    public static double div(double v1, double v2, int scale)
    {
        if (scale <0)
        {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        if (b1.compareTo(BigDecimal.ZERO) == 0)
        {
            return BigDecimal.ZERO.doubleValue();
        }
        return b1.divide(b2, scale, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * Cung cấp cách làm tròn số thập phân chính xác.
     * @param v cần được làm tròn
     * @param Thang  giữ lại một vài chữ số sau dấu thập phân
     * @return kết quả làm tròn
     */
    public static double round(double v, int scale)
    {
        if (scale < 0)
        {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, RoundingMode.HALF_UP).doubleValue();
    }
}
