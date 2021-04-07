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
@Table(name = "LTC$TRANSACTIONS")
public class Transaction {

    @Id
    @Column(name = "TX_ID", nullable = false)
    private String txId;

    @Column(name = "TX_HASH", nullable = false)
    private String txHash;

    @Column(name = "BLOCK_HASH", nullable = false)
    private String blockHash;

    @Column(name = "TX_VERSION", nullable = false)
    private Integer txVersion;

    @Column(name = "TX_SIZE", nullable = false)
    private Integer txSize;

    @Column(name = "TX_VSIZE", nullable = false)
    private Integer txVSize;

    @Column(name = "WEIGHT", nullable = false)
    private Integer weight;

    @Column(name = "LOCK_TIME", nullable = false)
    private Integer lockTime;
}
