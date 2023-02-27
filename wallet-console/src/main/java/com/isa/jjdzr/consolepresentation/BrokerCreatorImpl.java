package com.isa.jjdzr.consolepresentation;

import com.isa.jjdzr.dto.Wallet;

public class BrokerCreatorImpl implements BrokerCreator{
    @Override
    public BrokerBuy createBroker() {
        return new BrokerBuy();
    }

    @Override
    public BrokerBuy createBrokerWithWallet(Wallet wallet) {
        return null;
    }
}
