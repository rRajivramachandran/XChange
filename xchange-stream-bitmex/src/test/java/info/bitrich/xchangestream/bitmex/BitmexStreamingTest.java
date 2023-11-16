package info.bitrich.xchangestream.bitmex;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;

/** @author Foat Akhmadeev 13/06/2018 */
public class BitmexStreamingTest {
  private final ObjectMapper objectMapper = new ObjectMapper();
  @Test
  public void shouldGetCorrectSubscribeMessage() throws IOException {
    BitmexStreamingService service = new BitmexStreamingService("url", "api", "secret");

    Assert.assertEquals(
        objectMapper.readTree("{\"op\":\"subscribe\",\"args\":[\"name\"]}"), objectMapper.readTree(service.getSubscribeMessage("name")));
  }

  @Test
  public void shouldGetCorrectUnsubscribeMessage() throws IOException {
    BitmexStreamingService service = new BitmexStreamingService("url", "api", "secret");

    Assert.assertEquals(
        objectMapper.readTree("{\"op\":\"unsubscribe\",\"args\":[\"name\"]}"), objectMapper.readTree(service.getUnsubscribeMessage("name")));
  }
}
