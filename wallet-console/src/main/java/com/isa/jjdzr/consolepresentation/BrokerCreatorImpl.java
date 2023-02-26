package com.isa.jjdzr.consolepresentation;

import com.isa.jjdzr.dto.Wallet;

public class BrokerCreatorImpl implements BrokerCreator{
    @Override
    public Broker createBroker() {
        return new Broker();
    }

    @Override
    public Broker createBrokerWithWallet(Wallet wallet) {
        return null;
    }
}
