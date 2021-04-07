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
@Table(name = "LTC$BLOCKS")
public class Block {

    @Id
    @Column(name = "HASH", nullable = false)
    private String hash;

    @Column(name = "COUNT_CONFIRMATIONS", nullable = false)
    private Integer countConfirmations;

    @Column(name = "STRIPPED_SIZE", nullable = false)
    private Integer strippedSize;

    @Column(name = "BLOCK_SIZE", nullable = false)
    private Integer blockSize;

    @Column(name = "WEIGHT", nullable = false)
    private Integer weight;

    @Column(name = "HEIGHT", nullable = false)
    private Integer height;

    @Column(name = "VERSION", nullable = false)
    private Integer version;

    @Column(name = "VERSION_HEX", nullable = false)
    private String versionHex;

    @Column(name = "MERKLE_ROOT", nullable = false)
    private String merkleRoot;

    @Column(name = "BLOCK_TIME", nullable = false)
    private Integer blockTime;

    @Column(name = "MEDIAN_TIME", nullable = false)
    private Integer medianTime;

    @Column(name = "NONCE", nullable = false)
    private Long nonce;

    @Column(name = "BITS", nullable = false)
    private String bits;

    @Column(name = "DIFFICULTY", nullable = false)
    private Double difficulty;

    @Column(name = "CHAIN_WORK", nullable = false)
    private String chainWork;

    @Column(name = "TRANSACTION_COUNT", nullable = false)
    private Integer transactionCount;

    @Column(name = "PREVIOUS_BLOCK_HASH", nullable = false)
    private String previousBlockHash;

    @Column(name = "NEXT_BLOCK_HASH", nullable = false)
    private String nextBlockHash;
}
