package pl.edu.pjwstk.jaz;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;


@RestController
public class AverageController {
    @GetMapping("average")
    public AverageResult getAverage(@RequestParam(value = "numbers", required = false) String numbers) {

        if(numbers == null){
            return new AverageResult("Please put parameters.");
        }
        String[] num =numbers.split(",");
        double sum = 0;
        for (int j = 0; j < num.length; j++) {
            String i = num[j];
            sum += Integer.parseInt(i);
        }

        BigDecimal rA = new BigDecimal(sum / num.length);
        rA = rA.setScale(2, RoundingMode.HALF_UP).stripTrailingZeros();

        return new AverageResult("Average equals: "+rA);
    }
}
