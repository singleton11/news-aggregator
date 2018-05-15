package com.singleton.newsaggregator.util;

import com.singleton.newsaggregator.service.FeedParser;
import com.singleton.newsaggregator.service.HTMLFeedParser;
import com.singleton.newsaggregator.service.RSSFeedParser;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertTrue;

public class ParserFactoryTests {

    private ParserFactory parserFactory;

    public ParserFactoryTests() {
        var serviceMap = new HashMap<String, FeedParser>();
        serviceMap.put("RSSFeedParser", new RSSFeedParser());
        serviceMap.put("HTMLFeedParser", new HTMLFeedParser());
        parserFactory = new ParserFactory(serviceMap);
    }

    @Test
    public void returnsHTMLFeedParserInEmptyData() {
        assertTrue(parserFactory.getParserInstance("", null) instanceof HTMLFeedParser);
    }

    @Test
    public void returnsHTMLFeedParserOnCorrectHTML() {
        assertTrue(parserFactory.getParserInstance(
                "<html>" +
                        "</html>", null) instanceof HTMLFeedParser);
    }

    /**
     * Correct XML which is not valid RSS or Atom feed should be considered as HTML
     */
    @Test
    public void returnsHTMLFeedParserOnCorrectXML() {
        assertTrue(parserFactory.getParserInstance(
                "\n" +
                        "<note>\n" +
                        "<to>Tove</to>\n" +
                        "<from>Jani</from>\n" +
                        "<heading>Reminder</heading>\n" +
                        "<body>Don't forget me this weekend!</body>\n" +
                        "</note>", null) instanceof HTMLFeedParser);
    }

    @Test
    public void returnsRSSFeedParserOnAtomFeed() {
        assertTrue(parserFactory.getParserInstance(
                "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                        "<feed xmlns=\"http://www.w3.org/2005/Atom\">\n" +
                        "\n" +
                        "  <title>Example Feed</title>\n" +
                        "  <link href=\"http://example.org/\"/>\n" +
                        "  <updated>2003-12-13T18:30:02Z</updated>\n" +
                        "  <author>\n" +
                        "    <name>John Doe</name>\n" +
                        "  </author>\n" +
                        "  <id>urn:uuid:60a76c80-d399-11d9-b93C-0003939e0af6</id>\n" +
                        "\n" +
                        "  <entry>\n" +
                        "    <title>Atom-Powered Robots Run Amok</title>\n" +
                        "    <link href=\"http://example.org/2003/12/13/atom03\"/>\n" +
                        "    <id>urn:uuid:1225c695-cfb8-4ebb-aaaa-80da344efa6a</id>\n" +
                        "    <updated>2003-12-13T18:30:02Z</updated>\n" +
                        "    <summary>Some text.</summary>\n" +
                        "  </entry>\n" +
                        "\n" +
                        "</feed>", null) instanceof RSSFeedParser);
    }

    @Test
    public void returnsRSSFeedParserOnRSSFeed() {
        assertTrue(parserFactory.getParserInstance(
                "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                        "<rss version=\"2.0\">\n" +
                        "\n" +
                        "<channel>\n" +
                        "  <title>W3Schools Home Page</title>\n" +
                        "  <link>https://www.w3schools.com</link>\n" +
                        "  <description>Free web building tutorials</description>\n" +
                        "  <item>\n" +
                        "    <title>RSS Tutorial</title>\n" +
                        "    <link>https://www.w3schools.com/xml/xml_rss.asp</link>\n" +
                        "    <description>New RSS tutorial on W3Schools</description>\n" +
                        "  </item>\n" +
                        "  <item>\n" +
                        "    <title>XML Tutorial</title>\n" +
                        "    <link>https://www.w3schools.com/xml</link>\n" +
                        "    <description>New XML tutorial on W3Schools</description>\n" +
                        "  </item>\n" +
                        "</channel>\n" +
                        "\n" +
                        "</rss>", null) instanceof RSSFeedParser);
    }
}
