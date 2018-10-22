package pl.pawellewarski.Kwiaty.configure;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.pawellewarski.Kwiaty.model.dto.PostDTO;
import pl.pawellewarski.Kwiaty.model.entities.Post;


@Configuration
public class BasicConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(Post.class, PostDTO.class)
                .addMapping(pst -> pst.getUser().getId(), PostDTO::setIdOfUser)
                .addMapping(p -> p.getAuditEntity().getAdded(), PostDTO::setCreated);
        return modelMapper;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}



