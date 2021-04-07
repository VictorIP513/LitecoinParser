package litecoin_parser.database;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Accessors(chain = true)
@Entity
@IdClass(TxOutAddress.class)
@Table(name = "LTC$TX_OUT_ADDRESSES")
public class TxOutAddress implements Serializable {

    private static final long serialVersionUID = -5114972708534520721L;

    @Id
    @Column(name = "WALLET_ADDRESS", nullable = false)
    private String walletAddress;

    @Id
    @Column(name = "TX_ID", nullable = false)
    private String txId;

    @Id
    @Column(name = "OUTPUT_NUMBER", nullable = false)
    private Integer outputNumber;
}
