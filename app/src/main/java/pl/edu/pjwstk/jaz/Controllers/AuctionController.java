package pl.edu.pjwstk.jaz.Controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jaz.DataBase.AuctionEntity;
import pl.edu.pjwstk.jaz.DataBase.MiniatureEntity;
import pl.edu.pjwstk.jaz.DataBase.SectionService;
import pl.edu.pjwstk.jaz.DataBase.UserService;
import pl.edu.pjwstk.jaz.Requests.AuctionEditRequest;
import pl.edu.pjwstk.jaz.Requests.AuctionRequest;
import pl.edu.pjwstk.jaz.Requests.PhotosEditRequest;

import java.util.List;

@RestController
public class AuctionController {
    private final SectionService sectionService;
    private final UserService userService;

    public AuctionController(SectionService sectionService, UserService userService) {
        this.sectionService = sectionService;
        this.userService = userService;
    }

    @PreAuthorize("hasAnyAuthority('User')")
    @PostMapping("/addAuction")
    public void addNewAuction(@RequestBody AuctionRequest auctionRequest){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getPrincipal());
        Long owner_id = userService.getIdFromUser(String.valueOf(auth.getPrincipal()));
        sectionService.addAuction(auctionRequest.getCategory(),auctionRequest.getTitle(),auctionRequest.getDescription(),auctionRequest.getPrice(),owner_id,auctionRequest.getPhotos(),auctionRequest.getValues(),auctionRequest.getParameters());
    }

    @PreAuthorize("hasAnyAuthority('User')")
    @PostMapping("/editAuction")
    public void editAuction(@RequestBody AuctionEditRequest auctionEditRequest){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Edition auction by " + auth.getPrincipal());
        Long owner_id = userService.getIdFromUser(String.valueOf(auth.getPrincipal()));
        sectionService.editAuction(auctionEditRequest.getAuction_id(),owner_id,auctionEditRequest.getParameter(),auctionEditRequest.getValue(),auctionEditRequest.getNewTitle(),auctionEditRequest.getNewPrice());
    }

    @PreAuthorize("hasAnyAuthority('User')")
    @PostMapping("/editPhoto")
    public void editPhoto(@RequestBody PhotosEditRequest photosEditRequest){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Edition photos by " + auth.getPrincipal());
        Long owner_id = userService.getIdFromUser(String.valueOf(auth.getPrincipal()));
        sectionService.editPhoto(photosEditRequest.getPosition(),photosEditRequest.getNewLink(),owner_id,photosEditRequest.getAuction_id());
    }

    @PreAuthorize("hasAnyAuthority('User')")
    @PostMapping("/addPhoto")
    public void addPhoto(@RequestBody PhotosEditRequest photosEditRequest){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Edition photos by " + auth.getPrincipal());
        Long owner_id = userService.getIdFromUser(String.valueOf(auth.getPrincipal()));
        sectionService.addPhoto(photosEditRequest.getNewLink(),owner_id,photosEditRequest.getAuction_id());
    }

    @PreAuthorize("hasAnyAuthority('User')")
    @GetMapping("/getAuctionWithPhoto")
    public List<MiniatureEntity> getAuctionWithPhoto(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Get Auctions with miniature by " + auth.getPrincipal());
        Long owner_id = userService.getIdFromUser(String.valueOf(auth.getPrincipal()));
        return sectionService.getAuction(owner_id) ;
    }
}
