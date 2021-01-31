package greenfield.group.com.journalservice.gates;

import greenfield.group.com.gatecommon.SimpleResult;
import greenfield.group.com.gatecommon.Status;
import greenfield.group.com.journalservice.model.journal.JournalDataDTO;
import greenfield.group.com.journalservice.model.journal.JournalMetadataCommonDTO;
import greenfield.group.com.journalservice.requests.ButtonHandlerRequest;
import greenfield.group.com.journalservice.requests.FilterRequest;
import greenfield.group.com.journalservice.requests.LoadJournalRequest;
import greenfield.group.com.journalservice.requests.SavePresetRequest;
import greenfield.group.com.journalservice.response.JournalDataResponse;
import greenfield.group.com.journalservice.services.JournalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController(value = "/journal")
public class JournalGate {

    @Autowired
    private JournalServiceImpl journalServiceImpl;

    /**
     * Метод загрузки метаданных-журнала согласно его системному имени
     *
     * @param loadJournalRequest запрос с интерфейса при загрузке журнала
     * @return
     */
    @RequestMapping(path = "/load", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public SimpleResult<JournalMetadataCommonDTO> load(@RequestBody LoadJournalRequest loadJournalRequest) {
        return new SimpleResult<>(
                Status.OK, journalServiceImpl.metaDataLoadJournal(loadJournalRequest.getJournalSysName())
        );
    }

    /**
     * Метод загрузки данных журнала согласно его системному имени
     *
     * @param loadJournalRequest запрос с интерфейса при загрузке журнала
     * @return
     */
    @RequestMapping(path = "/loadData", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public SimpleResult<JournalDataResponse> loadData(
            @RequestHeader(value = "Authorization", defaultValue = "") String authorization,
            @RequestBody LoadJournalRequest loadJournalRequest
    ) {
        JournalDataDTO journalData = journalServiceImpl.loadJournalData(authorization,
                loadJournalRequest.getJournalSysName(),
                loadJournalRequest.getPageNumber()
        );
        JournalDataResponse journalDataResponse = new JournalDataResponse(journalData);
        return new SimpleResult<>(
                Status.OK
                , journalDataResponse
        );
    }

    /**
     * Метод фильтрации данных
     *
     * @param filterRequest запрос с интерфейса при загрузке журнала
     * @return
     */
    @RequestMapping(path = "/doFilter", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public SimpleResult<JournalDataResponse> doFilter(
            @RequestHeader(value = "Authorization", defaultValue = "") String authorization,
            @RequestBody FilterRequest filterRequest
    ) {
        JournalDataDTO journalData = journalServiceImpl.doFilter(authorization, filterRequest.getSysName(), filterRequest.getItemList());
        JournalDataResponse journalDataResponse = new JournalDataResponse(journalData);
        return new SimpleResult<>(
                Status.OK
                , journalDataResponse
        );
    }

    /**
     * Метод сохранения пресета для фильтра
     *
     * @param savePresetRequest запрос с интерфейса на сохранение пресета
     * @return
     */
    @RequestMapping(path = "/savePreset", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public SimpleResult savePreset(
            @RequestHeader(value = "Authorization", defaultValue = "") String authorization,
            @RequestBody SavePresetRequest savePresetRequest
    ) {
        return new SimpleResult<>(
                Status.OK
                , journalServiceImpl.savePreset(authorization, savePresetRequest.getPresetDTO())
        );
    }

    /**
     * Метод вызова обработчика кнопки
     *
     * @param buttonHandlerRequest
     * @return
     */
    @RequestMapping(path = "/doButtonHandler"
            , method = {RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.GET}
            , produces = MediaType.APPLICATION_JSON_VALUE
    )
    public SimpleResult doButtonHandler(
            @RequestHeader(value = "Authorization", defaultValue = "") String authorization,
            @RequestBody ButtonHandlerRequest buttonHandlerRequest) {
        JournalDataDTO journalData = journalServiceImpl.doButtonHandler(
                authorization,
                buttonHandlerRequest.getRequestMethod()
                , buttonHandlerRequest.getJournalSysName()
                , buttonHandlerRequest.getButtonAction()
                , buttonHandlerRequest.getEntity()
                , buttonHandlerRequest.getPageNumber()
        );
        JournalDataResponse journalDataResponse = new JournalDataResponse(journalData);

        return new SimpleResult<>(
                Status.OK
                , journalDataResponse
        );
    }

}
