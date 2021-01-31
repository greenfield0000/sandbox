package greenfield.group.com.journalservice.services;

import greenfield.group.com.journalservice.model.journal.JournalDataDTO;
import greenfield.group.com.journalservice.model.journal.JournalFilterItemDTO;
import greenfield.group.com.journalservice.model.journal.JournalMetadataCommonDTO;
import greenfield.group.com.journalservice.model.journal.PresetDTO;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * Сервис журнала
 */
public interface JournalService {
    /**
     * Загрузка журнала согласно параметрам
     *
     * @param sysName системное имя журнала
     * @return
     */
    JournalMetadataCommonDTO metaDataLoadJournal(String sysName);

    /**
     * Общая загрузка данных журнала по системному имени
     *
     *
     * @param authorization
     * @param sysName    системное имя журнала
     * @param pageNumber номер страницы
     * @return возвращает данные журнала
     */
    JournalDataDTO loadJournalData(String authorization, String sysName, int pageNumber);

    /**
     * Метод фильтрации данных
     *
     *
     * @param authorization
     * @param journalSysName        системное имя журнала
     * @param journalFilterItemDTOList список фильтров
     * @return
     */
    JournalDataDTO doFilter(String authorization, String journalSysName, List<JournalFilterItemDTO> journalFilterItemDTOList);

    /**
     * Метод сохранения пресета
     *
     *
     * @param authorization
     * @param presetDTO новый пресет
     * @return
     */
    List<PresetDTO> savePreset(String authorization, PresetDTO presetDTO);

    /**
     * Вызов обработчика кнопки
     *
     *
     * @param authorization
     * @param requestMethod
     * @param journalSysName системное имя журнала, для которого вызывается кнопка
     * @param buttonAction   системное имя кнопки
     * @param entity
     * @param pageNumber     страница, на которой остановился пользователь (нужна для возврата на нее после
     *                       выполнения действия)
     * @return данные журнала на странице pageNumber
     */
    JournalDataDTO doButtonHandler(String authorization, RequestMethod requestMethod, String journalSysName, String buttonAction, Map<String, Object> entity, int pageNumber);
}
