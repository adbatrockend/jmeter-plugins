package kg.apc.jmeter.samplers;

import java.io.File;
import java.io.IOException;
import java.nio.channels.spi.AbstractSelectableChannel;
import kg.apc.emulators.SocketChannelEmul;
import org.apache.jmeter.samplers.SampleResult;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author undera
 */
public class HTTPRawSamplerDirectFileTest {

    private class HTTPRawSamplerDirectFileEmul extends HTTPRawSamplerDirectFile {

        SocketChannelEmul sockEmul = new SocketChannelEmul();

        @Override
        protected AbstractSelectableChannel getChannel() throws IOException {
            return sockEmul;
        }
    }

    public HTTPRawSamplerDirectFileTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of processIO method, of class HTTPRawSamplerDirectFile.
     */
    @Test
    public void testProcessIO() throws Exception {
        System.out.println("processIO");
        SampleResult res = new SampleResult();
        res.sampleStart();
        HTTPRawSamplerDirectFileEmul instance = new HTTPRawSamplerDirectFileEmul();
        instance.setPort("0");
        String file = this.getClass().getResource("testSendFile.raw").getPath();
        instance.setRequestData(file);
        instance.processIO(res);
        assertEquals(new File(file).length(), instance.sockEmul.getWrittenBytesCount());
    }


    @Test
    public void testProcessIO_real() throws Exception {
        System.out.println("processIO_real_short");
        String file = this.getClass().getResource("testSendFile_1.raw").getPath();
        SampleResult res = new SampleResult();
        res.sampleStart();
        HTTPRawSamplerDirectFile instance = new HTTPRawSamplerDirectFile();
        instance.setPort("80");
        instance.setTimeout("15000");
        instance.setHostName("files.namba.kz");
        instance.setRequestData(file);
        byte[] resp = instance.processIO(res);
        assertTrue(resp.length>10);
    }

    @Test
    public void testProcessIO_realbig() throws Exception {
        System.out.println("processIO_real");
        String file = this.getClass().getResource("testSendFile.raw").getPath();
        SampleResult res = new SampleResult();
        res.sampleStart();
        HTTPRawSamplerDirectFile instance = new HTTPRawSamplerDirectFile();
        instance.setPort("80");
        instance.setTimeout("15000");
        instance.setHostName("files.namba.kz");
        instance.setRequestData(file);
        byte[] resp = instance.processIO(res);
        assertTrue(resp.length>10);
    }
}
