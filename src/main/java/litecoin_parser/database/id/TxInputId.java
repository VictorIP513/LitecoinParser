package litecoin_parser.database.id;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Data
@Accessors(chain = true)
@MappedSuperclass
public class TxInputId implements Serializable {

    private static final long serialVersionUID = -3894512815798317740L;

    @Id
    @Column(name = "TX_ID", nullable = false)
    private String txId;

    @Id
    @Column(name = "TX_INPUT_ID", nullable = false)
    private String transactionInputId;

    @Id
    @Column(name = "VOUT", nullable = false)
    private Integer vout;
}
