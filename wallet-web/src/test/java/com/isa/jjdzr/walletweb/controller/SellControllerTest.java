package com.isa.jjdzr.walletweb.controller;
import com.isa.jjdzr.walletcore.common.Constants;
import com.isa.jjdzr.walletcore.dto.WalletAsset;
import com.isa.jjdzr.walletweb.dto.SellInfoDto;
import com.isa.jjdzr.walletweb.service.WalletWebService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SellControllerTest {

    @Mock
    private WalletWebService walletWebService;

    @InjectMocks
    private SellController sellController;

    @Test
    void testGetSellWalletAsset() {
        Long waId = 1L;
        WalletAsset walletAsset = new WalletAsset();
        when(walletWebService.findWalletAsset(waId)).thenReturn(walletAsset);

        Model model = mock(Model.class);
        String view = sellController.getSellWalletAsset(waId, model);

        verify(model).addAttribute("walletAsset", walletAsset);
        verify(model).addAttribute(eq("sellInfo"), any(SellInfoDto.class));
        assertEquals("sell-asset", view);
    }

}
