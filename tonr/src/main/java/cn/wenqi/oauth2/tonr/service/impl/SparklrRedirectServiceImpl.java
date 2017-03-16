package cn.wenqi.oauth2.tonr.service.impl;

import cn.wenqi.oauth2.tonr.SparklrException;
import cn.wenqi.oauth2.tonr.service.SparklrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wenqi
 */
@Service
public class SparklrRedirectServiceImpl implements SparklrService {
    @Value("#{configProperties['sparklrPhotoListURL']}")
    private String sparklrPhotoListURL;
    @Value("#{configProperties['sparklrTrustedMessageURL']}")
    private String sparklrTrustedMessageURL;
    @Value("#{configProperties['sparklrPhotoURLPattern']}")
    private String sparklrPhotoURLPattern;
    @Autowired
    private RestOperations sparklrRedirectRestTemplate;
    @Autowired
    private RestOperations trustedClientRestTemplate;

    public List<String> getSparklrPhotoIds() throws SparklrException {
        try {
            InputStream photosXML = new ByteArrayInputStream(sparklrRedirectRestTemplate.getForObject(
                    URI.create(sparklrPhotoListURL), byte[].class));

            final List<String> photoIds = new ArrayList<String>();
            SAXParserFactory parserFactory = SAXParserFactory.newInstance();
            parserFactory.setValidating(false);
            parserFactory.setXIncludeAware(false);
            parserFactory.setNamespaceAware(false);
            SAXParser parser = parserFactory.newSAXParser();
            parser.parse(photosXML, new DefaultHandler() {
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes)
                        throws SAXException {
                    if ("photo".equals(qName)) {
                        photoIds.add(attributes.getValue("id"));
                    }
                }
            });
            return photoIds;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        } catch (SAXException e) {
            throw new IllegalStateException(e);
        } catch (ParserConfigurationException e) {
            throw new IllegalStateException(e);
        }
    }

    public InputStream loadSparklrPhoto(String id) throws SparklrException {
        return new ByteArrayInputStream(sparklrRedirectRestTemplate.getForObject(
                URI.create(String.format(sparklrPhotoURLPattern, id)), byte[].class));
    }

    public String getTrustedMessage() {
        return this.trustedClientRestTemplate.getForObject(URI.create(sparklrTrustedMessageURL), String.class);
    }
}
