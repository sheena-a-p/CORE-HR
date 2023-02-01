package com.core.hr.security.json;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class Json {

    public static final String TIME_ZONE = "JST";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_FORMAT = "HH:mm";

    @Retention(RetentionPolicy.RUNTIME)
    @JacksonAnnotationsInside
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT, timezone = TIME_ZONE)
    public @interface DateFormat {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @JacksonAnnotationsInside
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME_FORMAT, timezone = TIME_ZONE)
    public static @interface DateTimeFormat {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @JacksonAnnotationsInside
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = TIME_FORMAT, timezone = TIME_ZONE)
    public static @interface TimeFormat {
    }

    private Json() {
    }
}
