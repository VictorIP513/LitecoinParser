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
public class TxOutputId implements Serializable {

    private static final long serialVersionUID = -956805725524145296L;

    @Id
    @Column(name = "TX_ID", nullable = false)
    private String txId;

    @Id
    @Column(name = "OUTPUT_NUMBER", nullable = false)
    private Integer outputNumber;
}
