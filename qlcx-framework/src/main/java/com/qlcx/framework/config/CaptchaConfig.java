package com.qlcx.framework.config;

import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import static com.google.code.kaptcha.Constants.*;

/**
 * Verification code configuration
 */
@Configuration
public class CaptchaConfig
{
    @Bean(name = "captchaProducer")
    public DefaultKaptcha getKaptchaBean()
    {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        // Whether there is a border, the default is true, we can set yes, no
        properties.setProperty(KAPTCHA_BORDER, "yes");
        // The text color of the verification code defaults to Color.BLACK
        properties.setProperty(KAPTCHA_TEXTPRODUCER_FONT_COLOR, "black");
        // The width of the captcha image is 200 by default
        properties.setProperty(KAPTCHA_IMAGE_WIDTH, "160");
        // The height of the verification code image is 50 by default
        properties.setProperty(KAPTCHA_IMAGE_HEIGHT, "60");
        // The character size of the verification code text is 40 by default
        properties.setProperty(KAPTCHA_TEXTPRODUCER_FONT_SIZE, "38");
        // KAPTCHA_SESSION_KEY
        properties.setProperty(KAPTCHA_SESSION_CONFIG_KEY, "kaptchaCode");
        // The text length of the verification code defaults to 5
        properties.setProperty(KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "4");
        // The caption text font style defaults to new Font("Arial", 1, fontSize), new Font("Courier", 1, fontSize)
        properties.setProperty(KAPTCHA_TEXTPRODUCER_FONT_NAMES, "Arial,Courier");
        // Picture style watermark com.google.code.kaptcha.impl.WaterRipple fisheye com.google.code.kaptcha.impl.FishEyeGimpy shadow com.google.code.kaptcha.impl.ShadowGimpy
        properties.setProperty(KAPTCHA_OBSCURIFICATOR_IMPL, "com.google.code.kaptcha.impl.ShadowGimpy");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

    @Bean(name = "captchaProducerMath")
    public DefaultKaptcha getKaptchaBeanMath()
    {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        // Whether there is a border, the default is true, we can set yes, no
        properties.setProperty(KAPTCHA_BORDER, "yes");
        // The border color defaults to Color.BLACK
        properties.setProperty(KAPTCHA_BORDER_COLOR, "105,179,90");
        // The text color of the verification code defaults to Color.BLACK
        properties.setProperty(KAPTCHA_TEXTPRODUCER_FONT_COLOR, "blue");
        // The width of the captcha image is 200 by default
        properties.setProperty(KAPTCHA_IMAGE_WIDTH, "160");
        // The height of the verification code image is 50 by default
        properties.setProperty(KAPTCHA_IMAGE_HEIGHT, "60");
        // The character size of the verification code text is 40 by default
        properties.setProperty(KAPTCHA_TEXTPRODUCER_FONT_SIZE, "35");
        // KAPTCHA_SESSION_KEY
        properties.setProperty(KAPTCHA_SESSION_CONFIG_KEY, "kaptchaCodeMath");
        // captcha text generator
        properties.setProperty(KAPTCHA_TEXTPRODUCER_IMPL, "com.qlcx.framework.config.KaptchaTextCreator");
        // Verification code text character spacing default is 2
        properties.setProperty(KAPTCHA_TEXTPRODUCER_CHAR_SPACE, "3");
        // The text length of the verification code defaults to 5
        properties.setProperty(KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "6");
        // The caption text font style defaults to new Font("Arial", 1, fontSize), new Font("Courier", 1, fontSize)
        properties.setProperty(KAPTCHA_TEXTPRODUCER_FONT_NAMES, "Arial,Courier");
        // The color of the verification code noise is Color.BLACK by default
        properties.setProperty(KAPTCHA_NOISE_COLOR, "white");
        // Interference implementation class
        properties.setProperty(KAPTCHA_NOISE_IMPL, "com.google.code.kaptcha.impl.NoNoise");
        // Picture style watermark com.google.code.kaptcha.impl.WaterRipple fisheye com.google.code.kaptcha.impl.FishEyeGimpy shadow com.google.code.kaptcha.impl.ShadowGimpy
        properties.setProperty(KAPTCHA_OBSCURIFICATOR_IMPL, "com.google.code.kaptcha.impl.ShadowGimpy");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
