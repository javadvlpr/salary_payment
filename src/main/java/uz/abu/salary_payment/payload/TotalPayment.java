package uz.abu.salary_payment.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TotalPayment {
    private Integer totalAmount;
    private Integer birchokAmount;
    private Integer averloAmount;
    private Integer bichuvAmount;
    private Integer vishivkaAmount;
}
