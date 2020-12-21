package ssafy.musicD.Domain;

import java.io.Serializable;

import org.springframework.data.redis.core.RedisHash;

import lombok.Data;

@Data
@RedisHash
public class Token implements Serializable{
    private static final long serialVersionUID = -7353484588260422449L;
    private String email;
    private String refreshToken;
}
