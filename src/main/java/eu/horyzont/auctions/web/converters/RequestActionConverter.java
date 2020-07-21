package eu.horyzont.auctions.web.converters;

import eu.horyzont.auctions.web.data.RequestAction;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

//TODO remove
@Component
public class RequestActionConverter implements Converter<String, RequestAction> {
    @Override
    public RequestAction convert(String actionName) {
        return RequestAction.valueOf(actionName.toUpperCase());
    }
}
