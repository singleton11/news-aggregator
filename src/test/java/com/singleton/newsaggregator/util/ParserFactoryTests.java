package com.singleton.newsaggregator.util;

import com.singleton.newsaggregator.service.HTMLFeedParser;
import com.singleton.newsaggregator.service.RSSFeedParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestAppConfig.class)
public class ParserFactoryTests {
    @Autowired
    ParserFactory parserFactory;

    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void testEmptyStringReturnsHTMLFeedParser() {
        assertTrue(parserFactory
                           .getParserInstance("", new HashMap<>()) instanceof HTMLFeedParser);
    }

    @Test
    public void testCorrectXMLReturnsHTMLFeedParser() throws IOException {
        var content = readFileFromResource("classpath:feedsamples/correct.xml");
        var parser = parserFactory.getParserInstance(content, new HashMap<>());
        assertTrue(parser instanceof HTMLFeedParser);
    }

    @Test
    public void testCorrectHTMLReturnsHTMLFeedParser() throws IOException {
        var content = readFileFromResource("classpath:feedsamples/correct.html");
        var parser = parserFactory.getParserInstance(content, new HashMap<>());
        assertTrue(parser instanceof HTMLFeedParser);
    }

    @Test
    public void testCorrectRSSReturnsRSSFeedParser() throws IOException {
        var content = readFileFromResource("classpath:feedsamples/correct.rss");
        var parser = parserFactory.getParserInstance(content, new HashMap<>());
        assertTrue(parser instanceof RSSFeedParser);
    }

    @Test
    public void testCorrectAtomReturnsRSSFeedParser() throws IOException {
        var content = readFileFromResource("classpath:feedsamples/correct.atom");
        var parser = parserFactory.getParserInstance(content, new HashMap<>());
        assertTrue(parser instanceof RSSFeedParser);
    }

    private String readFileFromResource(String resourceName) throws IOException {
        var builder = new StringBuilder();
        var inputStream = applicationContext
                .getResource(resourceName)
                .getInputStream();
        var bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while((line = bufferedReader.readLine()) != null) {
            builder.append(line);
        }
        return builder.toString();
    }
}
