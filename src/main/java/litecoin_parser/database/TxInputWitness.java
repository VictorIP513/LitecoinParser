package litecoin_parser.database;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "LTC$TX_INPUT_WITNESS")
public class TxInputWitness {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "TX_ID", nullable = false)
    private String txId;

    @Column(name = "TX_INPUT_ID", nullable = false)
    private String txInputId;

    @Column(name = "VOUT", nullable = false)
    private Integer vout;

    @Column(name = "WITNESS_DATA", nullable = false)
    private String witnessData;
}
