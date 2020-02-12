package com.training.session11.test;

import com.training.common.util.BeanLoader;
import com.training.session11.cartfullreqresp.adapter.outbound.ItemDTO;
import com.training.session11.cartfullreqresp.adapter.outbound.OutBoundItemRest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LoadItemTest implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        OutBoundItemRest outBoundItemRest = BeanLoader.loadBean(OutBoundItemRest.class);

        ItemDTO a4000 = outBoundItemRest.loadItemById("A4000").orElseThrow(()->new RuntimeException("Item Not Fond"));
        System.out.println("a4000 = " + a4000);

        ItemDTO a40001 = outBoundItemRest.loadItemById("A4000").orElseThrow(()->new RuntimeException("Item Not Fond"));
        System.out.println("a40001 = " + a40001);

    }
}
