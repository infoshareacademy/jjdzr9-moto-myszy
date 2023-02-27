package com.isa.jjdzr.consolepresentation;

import com.isa.jjdzr.dto.Wallet;

public interface BrokerCreator {
    BrokerBuy createBroker();
    BrokerBuy createBrokerWithWallet(Wallet wallet);
}
