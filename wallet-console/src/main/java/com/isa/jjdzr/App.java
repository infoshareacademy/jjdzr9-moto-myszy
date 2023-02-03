package com.isa.jjdzr;


import com.isa.jjdzr.assets.Assets;

import java.math.BigDecimal;

public class App
{
    public static void main( String[] args )
    {
        Assets a = new Assets("a",new BigDecimal(300));
        Assets b = new Assets("b", BigDecimal.valueOf(a.getCurrentPrice().doubleValue()));
        a.setCurrentPrice(a.getCurrentPrice().add(BigDecimal.valueOf(200)));
        System.out.println(b.getCurrentPrice());
    }
}
