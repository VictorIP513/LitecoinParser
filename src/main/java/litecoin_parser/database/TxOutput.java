package litecoin_parser.database;

import litecoin_parser.database.id.TxOutputId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Entity
@IdClass(TxOutputId.class)
@Table(name = "LTC$TX_OUTPUTS")
public class TxOutput extends TxOutputId {

    private static final long serialVersionUID = 7839675510051342404L;

    @Column(name = "OUT_VALUE", nullable = false)
    private Double outValue;

    @Column(name = "SCRIPT_ASM")
    private String scriptAsm;

    @Column(name = "SCRIPT_HEX")
    private String scriptHex;

    @Column(name = "SCRIPT_REQ_SIGNATURES")
    private Integer scriptReqSignatures;

    @Column(name = "SCRIPT_TYPE", nullable = false)
    private String scriptType;

    @Column(name = "IS_SPENT")
    private Boolean isSpent;
}
