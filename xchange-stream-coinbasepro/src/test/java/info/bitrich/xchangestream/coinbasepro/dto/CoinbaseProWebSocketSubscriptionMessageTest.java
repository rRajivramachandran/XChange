package info.bitrich.xchangestream.coinbasepro.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import info.bitrich.xchangestream.core.ProductSubscription;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.knowm.xchange.currency.CurrencyPair;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

/** Created by luca on 5/3/17. */
public class CoinbaseProWebSocketSubscriptionMessageTest {

  @Test
  public void testWebSocketMessageSerialization() throws JsonProcessingException, JSONException {

    ProductSubscription productSubscription =
        ProductSubscription.create()
            .addOrderbook(CurrencyPair.BTC_USD)
            .addTrades(CurrencyPair.BTC_USD)
            .addTicker(CurrencyPair.BTC_USD)
            .build();
    CoinbaseProWebSocketSubscriptionMessage message =
        new CoinbaseProWebSocketSubscriptionMessage("subscribe", productSubscription, CoinbaseProOrderBookMode.Default, null);

    final ObjectMapper mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

    String serialized = mapper.writeValueAsString(message);

    JSONAssert.assertEquals(
        "{\"type\":\"subscribe\",\"channels\":[{\"name\":\"matches\",\"product_ids\":[\"BTC-USD\"]},{\"name\":\"ticker\",\"product_ids\":[\"BTC-USD\"]},{\"name\":\"level2\",\"product_ids\":[\"BTC-USD\"]}]}",
        serialized, JSONCompareMode.NON_EXTENSIBLE);
  }

  @Test
  public void testWebSocketMessageSerializationL3Orderbook() throws JsonProcessingException, JSONException {

    ProductSubscription productSubscription =
        ProductSubscription.create()
            .addOrderbook(CurrencyPair.BTC_USD)
            .addTrades(CurrencyPair.BTC_USD)
            .addTicker(CurrencyPair.BTC_USD)
            .build();
    CoinbaseProWebSocketSubscriptionMessage message =
        new CoinbaseProWebSocketSubscriptionMessage("subscribe", productSubscription, CoinbaseProOrderBookMode.Full, null);

    final ObjectMapper mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

    String serialized = mapper.writeValueAsString(message);

    JSONAssert.assertEquals(
        "{\"type\":\"subscribe\",\"channels\":[{\"name\":\"matches\",\"product_ids\":[\"BTC-USD\"]},{\"name\":\"ticker\",\"product_ids\":[\"BTC-USD\"]},{\"name\":\"full\",\"product_ids\":[\"BTC-USD\"]}]}",
        serialized, JSONCompareMode.NON_EXTENSIBLE);
  }

  @Test
  public void testWebSocketMessageSerializationBatch() throws JsonProcessingException, JSONException {

    ProductSubscription productSubscription =
            ProductSubscription.create()
                    .addOrderbook(CurrencyPair.BTC_USD)
                    .addTrades(CurrencyPair.BTC_USD)
                    .addTicker(CurrencyPair.BTC_USD)
                    .build();
    CoinbaseProWebSocketSubscriptionMessage message =
            new CoinbaseProWebSocketSubscriptionMessage("subscribe", productSubscription, CoinbaseProOrderBookMode.Batch, null);

    final ObjectMapper mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

    String serialized = mapper.writeValueAsString(message);

    JSONAssert.assertEquals(
            "{\"type\":\"subscribe\",\"channels\":[{\"name\":\"matches\",\"product_ids\":[\"BTC-USD\"]},{\"name\":\"level2_batch\",\"product_ids\":[\"BTC-USD\"]},{\"name\":\"ticker\",\"product_ids\":[\"BTC-USD\"]}]}",
            serialized, JSONCompareMode.NON_EXTENSIBLE);
  }
}