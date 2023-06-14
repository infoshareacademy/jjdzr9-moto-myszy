package com.isa.jjdzr.walletweb.controller;
import com.isa.jjdzr.walletcore.dto.Asset;
import com.isa.jjdzr.walletcore.market.Market;
import com.isa.jjdzr.walletweb.dto.BuyInfoDto;
import com.isa.jjdzr.walletweb.service.WalletWebService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class BuyControllerTest {
    @Mock
    private WalletWebService walletWebService;

    @Mock
    private Market market;

    @InjectMocks
    private BuyController buyController;

    @Captor
    private ArgumentCaptor<Model> modelCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBuyAsset() {
        // Given
        String assetId = "123";
        Long walletId = 456L;
        Asset asset = new Asset();
        asset.setName("Example Asset");
        asset.setCurrentPrice(BigDecimal.valueOf(100));

        when(market.findById(assetId)).thenReturn(asset);

        Model model = mock(Model.class);

        // When
        String result = buyController.getBuyAsset(assetId, walletId, model);

        // Then
        assertEquals("buy-asset", result);
        verify(market).findById(assetId);

        ArgumentCaptor<BuyInfoDto> buyInfoCaptor = ArgumentCaptor.forClass(BuyInfoDto.class);
        verify(model).addAttribute(eq("buyInfo"), buyInfoCaptor.capture());

        BuyInfoDto buyInfo = buyInfoCaptor.getValue();
        assertNotNull(buyInfo);
        assertEquals(assetId, buyInfo.getAssetId());
        assertEquals("Example Asset", buyInfo.getAssetName());
        assertEquals(walletId, buyInfo.getWalletId());
        assertEquals(asset.getCurrentPrice(), buyInfo.getPrice());
    }

}
