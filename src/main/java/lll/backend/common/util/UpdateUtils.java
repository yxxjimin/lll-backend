package lll.backend.common.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.util.Arrays;

public class UpdateUtils {

    public static String[] getNullPropertyNames(Object source) {
        BeanWrapper wrapper = new BeanWrapperImpl(source);
        return Arrays.stream(wrapper.getPropertyDescriptors())
                .map(PropertyDescriptor::getName)
                .filter(propertyName -> {
                    Object value = wrapper.getPropertyValue(propertyName);
                    if (value == null) {
                        return true;
                    }
                    if (value instanceof String) {
                        return !StringUtils.hasText((String) value);
                    }
                    return false;
                })
                .distinct()
                .toArray(String[]::new);
    }

    /**
     * Must have matching property names
     */
    public static void copyNonNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }
}
