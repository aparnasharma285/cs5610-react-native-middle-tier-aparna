package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webdev.models.ListType;
import webdev.models.Topic;
import webdev.models.Widget;
import webdev.repositories.TopicRepository;
import webdev.repositories.WidgetRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class WidgetService {

    private final WidgetRepository widgetRepository;
    private final TopicRepository topicRepository;

    @Autowired
    public WidgetService(WidgetRepository widgetRepository, TopicRepository topicRepository) {
        this.widgetRepository = widgetRepository;
        this.topicRepository = topicRepository;
    }


    @GetMapping("/api/widget")
    public List<Widget> findAllWidget() {

        return widgetRepository.findAll();
    }

    @GetMapping("/api/widget/{widgetId}")
    public Widget findWidgetById(@PathVariable("widgetId") int widgetId) {
        return widgetRepository.findById(widgetId).orElse(null);
    }

    @GetMapping("/api/topic/{topicId}/widget")
    public List<Widget> findWidgetByTopic(@PathVariable("topicId") int topicId) {

        Topic topic = topicRepository.findById(topicId).orElse(null);

        if (topic != null) {

            return widgetRepository.findSortedWidgetsForTopic(topic);
        }

        return null;
    }

    @PostMapping("/api/topic/{topicId}/widget")
    public Widget createWidget(@PathVariable("topicId") int topicId, @RequestBody Widget widget) {

        Topic existingTopic = topicRepository.findById(topicId).orElse(null);

        if (existingTopic != null) {

            widget.setTopic(existingTopic);
            return widgetRepository.save(widget);
        }

        return null;
    }

    @PostMapping("api/widget/save/{topicId}")
    public List<Widget> saveAllWidgets(@PathVariable("topicId") int topicId,@RequestBody List<Widget> widgets){
        List<Widget> newWidgetList = new ArrayList<Widget>();
        Topic topic = topicRepository.findById(topicId).orElse(null);

        if(topic != null) {
            for(Widget w: topic.getWidgets()){
                widgetRepository.delete(w);
            }

            for (Widget widget : widgets) {
                widget.setTopic(topic);
                newWidgetList.add(widgetRepository.save(widget));
            }
        }
        return newWidgetList;
    }

    @DeleteMapping("/api/widget/{widgetId}")
    public void deleteWidget(@PathVariable("widgetId") int widgetId) {
        Widget widget = widgetRepository.findById(widgetId).orElse(null);

        if (widget != null) {
            widgetRepository.delete(widget);
        }
    }

}
