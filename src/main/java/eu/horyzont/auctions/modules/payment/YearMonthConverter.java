package eu.horyzont.auctions.modules.payment;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.YearMonth;

@Converter
public class YearMonthConverter implements AttributeConverter<YearMonth, String> {
    @Override
    public String convertToDatabaseColumn(YearMonth yearMonth) {
        return null;
    }

    @Override
    public YearMonth convertToEntityAttribute(String s) {
        return null;
    }
}
