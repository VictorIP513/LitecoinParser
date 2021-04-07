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
@Table(name = "LTC$TX_COINBASE_INPUTS")
public class TxCoinbaseInput {

    @Id
    @Column(name = "TX_ID", nullable = false)
    private String txId;

    @Column(name = "COINBASE", nullable = false)
    private String coinbase;

    @Column(name = "SEQUENCE_NUMBER", nullable = false)
    private Long sequenceNumber;
}
