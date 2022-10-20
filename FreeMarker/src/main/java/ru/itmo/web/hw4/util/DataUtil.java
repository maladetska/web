package ru.itmo.web.hw4.util;

import ru.itmo.web.hw4.model.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DataUtil {
    private static final List<User> USERS = Arrays.asList(
            new User(1, "MikeMirzayanov", "Mike Mirzayanov", Color.RED),
            new User(6, "pashka", "Pavel Mavrin", Color.RED),
            new User(9, "geranazarov555", "Georgiy Nazarov", Color.BLUE),
            new User(11, "tourist", "Gennady Korotkevich", Color.GREEN)
    );

    private static final List<Post> POSTS = Arrays.asList(
            new Post(1,
                    "To be, or not to be",
                    "To be, or not to be, that is the question:\n" +
                            "Whether 'tis nobler in the mind to suffer\n" +
                            "The slings and arrows of outrageous fortune,\n" +
                            "Or to take arms against a sea of troubles\n" +
                            "And by opposing end them?\n" +
                            "To die: to sleep;\n" +
                            "No more; and, by a sleep to say we end\n" +
                            "The heart-ache and the thousand natural shocks\n" +
                            "That flesh is heir to, 'tis a consummation\n" +
                            "Devoutly to be wish'd. To die, to sleep;\n" +
                            "To sleep: perchance to dream: ay, there's the rub;\n" +
                            "For in that sleep of death what dreams may come\n" +
                            "When we have shuffled off this mortal coil,\n" +
                            "Must give us pause.",
                    1),
            new Post(2,
                    "Планы на будущее",
                    "В этом году осуществилась моя мечта – я переехал жить в столицу. Это новый этап в моей жизни. Я арендовал квартиру и устроился на работу.\n" +
                            "\n" +
                            "Так как у меня перспективная и высокооплачиваемая должность, было решено инвестировать свои доходы. Прежде всего, планирую купить земельный участок для строительства своего дома. Пока риелтор ищет подходящий вариант, я занимаюсь поиском строительной фирмы.\n" +
                            "\n" +
                            "У меня есть автомобиль, купленный десять лет назад. Недавно стал замечать, что машина стала часто ломаться. Несколько раз я ездил на станцию технического обслуживания. Но проблема не решилась. Машину нужно продавать. Поэтому, думаю, что в следующем году у меня получится купить новый автомобиль. Хочу поменять тип кузова на кроссовер или внедорожник. Сейчас у меня универсал, раньше был седан.\n" +
                            "\n" +
                            "Надеюсь, что за пару лет у меня получится осуществить свои планы – закончить строительство дома, сделать ремонт, облагородить участок и купить машину.",
                    9),
            new Post(3,
                    "Выходные в Санкт-Петербурге",
                    "На прошлых выходных я решил съездить в Санкт-Петербург. Я так давно не был в этом красивом городе. Этому не может быть оправданий. Поездка на скоростном поезде занимает всего 4 часа, а это расстояние в семьсот километров. Помню, когда был школьником, мы ездили туда на каникулы, и дорога занимала всю ночь. Я зашел на сайт Российских Железных Дорог и заказал билеты из Москвы в Санкт-Петербург и обратно за шесть тысяч рублей на вечер пятницы.\n" +
                            "\n" +
                            "В пятницу я отпросился с работы пораньше и сразу отправился на Ленинградский вокзал.Хорошо, что он рядом с метро в центре города, и я успел на поезд. В тот же день я был в другом городе. Я провел два замечательных дня в Северной Столице. Посетил Эрмитаж и Зимний Дворец, прокатился на корабле по Неве, посмотрел на разводные мосты. Я вернулся домой в воскресенье вечером, радостный, полный впечатлений и отдохнувший. Надо будет чаще выбираться в Питер.",
                    9),
            new Post(4,
                    "Времена года",
                    "В России четыре времени года: весна, лето, осень и зима.\n" +
                            "\n" +
                            "Весной на смену суровым зимним морозам приходит тепло и природа оживает. В середине марта начинает таять снег и распускаются первые цветы. На деревьях появляются листья. Дни становятся длиннее.\n" +
                            "\n" +
                            "Лето в России довольно короткое, но теплое, а иногда жаркое. Длинные летние дни –излюбленное время для всех птиц и животных. Летом мы любим плавать, загорать и ездить на природу или на море.\n" +
                            "\n" +
                            "За летом приходит осень – самый дождливый сезон. Это очень красивое время года, когда листья меняют цвет и вспыхивают разными красками: желтым, оранжевым, красным. С первыми заморозками они начинают опадать. Погода становится пасмурной, а дни – мрачными.\n" +
                            "\n" +
                            "Морозная снежная зима – символ России. Она начинается в конце ноября и длится до середины марта. Реки покрываются льдом. Дни становятся короче. Рано темнеет. Январь – самый холодный месяц, именно после Нового Года случаются знаменитые русские морозы с температурой ниже 30 градусов.",
                    9),
            new Post(11,
                    "Le Petit Chaperon rouge.",
                    " était une fois une petite fille. Sa mère a fait pour elle un beau chaperon rouge.\n" +
                            "\n" +
                            "Elle le portait toujours et on a commencé à l'appeler Le Petit Chaperon rouge.\n" +
                            "\n" +
                            "Sa grand-mère vivait dans un autre village. Et un jour, Le Petit Chaperon rouge est allée la voir. Elle devait traverser la forêt. Dans la forêt elle a rencontré un loup. Le loup a décidé de ruser.\n" +
                            "\n",
                    11),
            new Post(67,
                    "The Tiger",
                    "Tiger! Tiger! burning bright\n" +
                            "In the forests of the night,\n" +
                            "What immortal hand or eye\n" +
                            "Could frame thy fearful symmetry?",
                    1),
            new Post(36,
                    "Sonnet 18",
                    "SHALL I compare thee to a summer's day?\n" +
                            "Thou art more lovely and more temperate.\n" +
                            "Rough winds do shake the darling buds of May,\n" +
                            "And summer's lease hath all too short a date:\n" +
                            "Sometime too hot the eye of heaven shines,\n" +
                            "And often is his gold complexion dimm'd;\n" +
                            "And every fair from fair sometimes declines,\n" +
                            "By chance, or nature's changing course, untrimm'd;\n" +
                            "But thy eternal summer shall not fade\n" +
                            "Nor lose possession of that fair thou ow'st;\n" +
                            "Nor shall Death brag thou wand'rest in his shade,\n" +
                            "When in eternal lines to time thou grow'st.\n" +
                            "So long as men can breathe or eyes can see,\n" +
                            "So long lives this, and this gives life to thee.",
                    1),
            new Post(112,
                    "Desiderata",
                    "GO placidly amid the noise and haste, and remember what peace there may be in silence. As far as possible without surrender be on good terms with all persons. Speak your truth quietly and clearly; and listen to others, even the dull and ignorant; they too have their story. Avoid loud and aggressive persons, they are vexatious to the spirit. If you compare yourself with other, you may become vain and bitter; for always there will be greater and lesser persons than yourself. Enjoy your achievements as well as your plans. Keep interested in your own career, however humble; it is a real possession in the changing fortunes of time. Exercise caution in your business affairs; for the world is full of trickery. But let this not blind you to what virtue there is; many persons strive for high ideals; and everywhere life is full of heroism. Be yourself. Especially, do not feign affection. Neither be cynical about love; for in the face of all aridity and disenchantment it is perennial as the grass. Take kindly the counsel of the years, gracefully surrendering the things of youth. Nurture strength of spirit to shield you in sudden misfortune. But do not distress yourself with imaginings. Many fears are born of fatigue and loneliness. Beyond a wholesome discipline, be gentle with yourself. You are a child of the universe, no less than the trees and the stars; you have a right to be here. And whether or not it is clear to you, no doubt the universe is unfolding as it should. Therefore be at peace with God, whatever you conceive Him to be, and whatever your labors and aspirations, in the noisy confusion of life keep peace with your soul. With all its sham, drudgery and broken dreams, it is still a beautiful world. Be careful. Strive to be happy.",
                    11)
    );

    public static void addData(HttpServletRequest request, Map<String, Object> data) {
        data.put("users", USERS);
        data.put("posts", POSTS);
        data.put("uri", request.getRequestURI());

        for (User user : USERS) {
            if (Long.toString(user.getId()).equals(request.getParameter("logged_user_id"))) {
                data.put("user", user);
            }
        }
    }
}
