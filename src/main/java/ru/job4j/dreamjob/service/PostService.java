package ru.job4j.dreamjob.service;

import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.persistence.PostStore;

import java.util.Collection;

/**
 * Слоеная архитектура
 *
 * Чистая Архитектура для веб-приложений
 *
 * Основные идеи заложенные в архитектуру:
 * 1) Независимость от фреймворка. Архитектура не зависит от существования какой-либо библиотеки. Это позволяет
 * использовать фреймворк в качестве инструмента, вместо того, чтобы втискивать свою систему в рамки его ограничений.
 * 2) Тестируемость. Бизнес-правила могут быть протестированы без пользовательского интерфейса, базы данных,
 * веб-сервера или любого другого внешнего компонента.
 * 3) Независимоcть от UI. Пользовательский интерфейс можно легко изменить, не изменяя остальную систему.
 * Например, веб-интерфейс может быть заменен на консольный, без изменения бизнес-правил.
 * 4) Независимоcть от базы данных. Вы можете поменять Oracle или SQL Server на MongoDB, BigTable, CouchDB и тд.
 * Ваши бизнес-правила не связаны с базой данных.
 * 5) Независимость от какого-либо внешнего сервиса. По факту ваши бизнес правила просто ничего не знают о внешнем мире.
 *
 * Любое веб приложение можно представить в виде слоев пирога:
 * Controllers -> Service -> Persistence -> DB
 *
 * Верхний слой знает, кто идет за ним. В свою очередь, нижний слой не знает, кто его использует.
 * Этот принцип отображается в принципе инверсии зависимости из SOLID.
 *
 * Например, Слой контроллеров может использовать слой сервисов. Слой сервисов не может использовать слой контроллеров.
 * Такая схема позволяет удобно расширять приложение. Слой не может перепрыгнуть через слой. То есть контроллер
 * не может напрямую вызвать слой персистенции. Слой - это классы имеющую одну функциональную принадлежность.
 *
 * Описание слоев:
 * 1) Слой контроллеры - это классы для работы с клиентом. Эти классы принимают запросы и отдают ответы от клиента.
 * Клиентом может быть: браузер, мобильное приложение, или другое приложение на Java.
 * 2) Слой сервисы - это классы выполняющую бизнес логику приложения. В слое сервисов используется только бизнес логика.
 * Здесь не должно быть работы с базой данных или c HTML. На качественно написанный слой сервисов легко написать
 * модульные тесты, потому что у нас нет привязки к внешним ресурсам.
 * 3) Слой персистенции - это классы для работы с базами данных. Здесь идут коннекты к базе, запросы, вставка.
 *
 * Важно. Любое веб приложение должно иметь минимум три слоя: controllers, service, persistence.
 * Даже если у вас сейчас нет бизнес логики, вы все равно создаете слой service
 * и делаете сквозные вызовы классов персистенции.
 *
 * @author Alex_life
 * @version 1.0
 * @since 08.10.2022
 */
@Service
public class PostService {
    private static final PostService INST = new PostService();
    private final PostStore storePost = PostStore.instOf();

    public static PostService instOf() {
        return INST;
    }

    public Collection<Post> findAll() {
        return storePost.findAll();
    }

    public void addPost(Post candidate) {
        storePost.addPost(candidate);
    }

    public Post findByIdPost(int id) {
        return storePost.findByIdPost(id);
    }

    public void updatePost(Post candidate) {
        storePost.updatePost(candidate);
    }
}
