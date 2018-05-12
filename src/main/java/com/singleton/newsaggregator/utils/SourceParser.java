package com.singleton.newsaggregator.utils;

import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import org.jsoup.Jsoup;
import org.xml.sax.InputSource;

import java.io.StringReader;


public class SourceParser {
    /**
     * Opens data passed as a string and returns according parser object (HTML or RSS/Atom feed
     * parser)
     *
     * @param data feed content to be parsed
     * @return Document or SyndFeed instance which ready to parse content
     */
    public static Object getParserObject(String data) {
        try {
            return new SyndFeedInput().build(new InputSource(new StringReader(data)));
        } catch (FeedException | IllegalArgumentException e) {
            return Jsoup.parse(data);
        }
    }
}
