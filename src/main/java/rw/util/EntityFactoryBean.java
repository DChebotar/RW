package rw.util;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by Chebotar_do on 28.05.2019.
 */

@Component
public class EntityFactoryBean {

    @Bean
    public CarrageFactory getCarrageFactory(){
        return new CarrageFactory();
    }
}
