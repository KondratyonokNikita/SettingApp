package internal.utils;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

/**
 * Created by Samsung on 22.05.2017.
 */
public class LocalDateAdapter extends XmlAdapter {

    @Override
    public Object unmarshal(Object v) throws Exception {
        return LocalDate.parse((String)v);
    }

    @Override
    public Object marshal(Object v) throws Exception {
        return v.toString();
    }
}