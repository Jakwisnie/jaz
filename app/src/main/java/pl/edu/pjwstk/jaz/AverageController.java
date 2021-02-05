package pl.edu.pjwstk.jaz;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;



@RestController
public class AverageController {

    @GetMapping("average") //value = "numbers", required = false
    public AverageResult getAverage(@RequestParam(value = "numbers", required = false) String numbers) {
        if ( numbers == null ) return new AverageResult("Please put parameters.");
        String[] query = numbers.split(",");
        double sum = 0;
        for(int i = 0; i < query.length; i++){
            sum += Integer.parseInt(query[i]);
        }
        return new AverageResult("Average equals: " + (new BigDecimal(sum/query.length).setScale(2, RoundingMode.HALF_UP).stripTrailingZeros()));
    }
}
