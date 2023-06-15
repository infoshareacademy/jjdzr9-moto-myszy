package com.isa.jjdzr.walletweb.controller;
import com.isa.jjdzr.walletcore.dto.Wallet;
import com.isa.jjdzr.walletweb.dto.DetailedWalletAssetDto;
import com.isa.jjdzr.walletweb.dto.TopUpDto;
import com.isa.jjdzr.walletweb.service.WalletWebService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class WalletControllerTest {

    @InjectMocks
    private WalletController walletController;

    @Mock
    private WalletWebService walletWebService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateWallet() {
        String viewName = walletController.createWallet(model);
        verify(model).addAttribute("wallet", new Wallet());
        assertEquals("create-wallet", viewName);
    }

    @Test
    void testShowWallet() {
        Long walletId = 1L;
        Wallet wallet = new Wallet();
        wallet.setCash(BigDecimal.valueOf(1000.00));
        List<DetailedWalletAssetDto> walletAssets = Collections.singletonList(new DetailedWalletAssetDto());

        when(walletWebService.find(walletId)).thenReturn(wallet);
        when(walletWebService.prepareDetailedWalletAssetDtos(walletId)).thenReturn(walletAssets);

        String viewName = walletController.showWallet(walletId, model);

        verify(walletWebService).createWalletChart(walletAssets, wallet);
        verify(model).addAttribute("walletAssets", walletAssets);
        verify(model).addAttribute("cash", wallet.getCash());
        assertEquals("wallet-view", viewName);
    }

    @Test
    void testLoadWallet() {
        Long userId = 1L;
        List<Wallet> walletList = Collections.singletonList(new Wallet());

        when(walletWebService.getUserWallets(userId)).thenReturn(walletList);

        String viewName = walletController.loadWallet(model, userId);

        verify(model).addAttribute(walletList);
        assertEquals("load-wallet", viewName);
    }

    @Test
    void testCreateWallet_withBindingResult() {
        Long userId = 1L;
        Wallet wallet = new Wallet();
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();
        MockHttpSession session = new MockHttpSession();

        when(bindingResult.hasErrors()).thenReturn(false);

        String viewName = walletController.createWallet(wallet, bindingResult, redirectAttributes, session, userId);

        verify(walletWebService).saveWallet(wallet);
        assertEquals("redirect:/wallet-view/" + wallet.getId(), viewName);
    }

    @Test
    void testHandleWalletLoading() {
        Long walletId = 1L;
        Wallet wallet = new Wallet();
        wallet.setId(walletId);
        MockHttpSession session = new MockHttpSession();

        when(walletWebService.find(walletId)).thenReturn(wallet);

        String viewName = walletController.handleWalletLoading(walletId, session);

        assertEquals(walletId, session.getAttribute("wallet"));
        assertEquals("redirect:/wallet-view/" + walletId, viewName);
    }

    @Test
    void testTopUpWallet() {
        Long walletId = 1L;

        String viewName = walletController.topUpWallet(walletId, model);

        verify(model).addAttribute("topUpDto", new TopUpDto());
        assertEquals("top-up-wallet", viewName);
    }

    @Test
    void testHandleTopUp() {
        Long walletId = 1L;
        TopUpDto topUpDto = new TopUpDto();
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

        when(bindingResult.hasErrors()).thenReturn(false);

        String viewName = walletController.handleTopUp(walletId, topUpDto, bindingResult, redirectAttributes);

        verify(walletWebService).topUpWallet(walletId, topUpDto);
        assertEquals("redirect:/wallet-view/" + walletId, viewName);
    }

}