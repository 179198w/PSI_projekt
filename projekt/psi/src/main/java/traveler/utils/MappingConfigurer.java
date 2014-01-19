package traveler.utils;

import ma.glasnost.orika.MapperFactory;

public interface MappingConfigurer {
    
    void configure(MapperFactory factory);
    
}