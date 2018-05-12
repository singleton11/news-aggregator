package com.singleton.newsaggregator.services.internal;

import com.singleton.newsaggregator.services.SourceTypeDeterminator;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;

import java.io.StringReader;

/**
 * Basic implementation of source type determination.
 * <p>
 * Algorithm is super easy: if string data passed to `determine` method opens as RSS/Atom feed. It's
 * RSS/Atom, HTML otherwise
 * </p>
 */
@Service
public class SourceTypeDeterminatorImpl implements SourceTypeDeterminator {

    public static boolean TYPE_HTML = true;
    public static boolean TYPE_RSS = false;

    private Object data = null;

    @Override
    public boolean determine(String data) {
        try {
            this.data = new SyndFeedInput().build(new InputSource(new StringReader(data)));
            return TYPE_RSS;
        } catch (FeedException e) {
            this.data = Jsoup.parse(data);
            return TYPE_HTML;
        }
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
