package litecoin_parser.database;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "LTC$ADDRESSES")
public class Address {

    @Id
    @Column(name = "WALLET_ADDRESS", nullable = false)
    private String walletAddress;
}
