package codrut.codru.cordu.AnimeRecommendations;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {

    private static String[] animes = new String[]{ "Fullmetal Alchemist Brotherhood",
            "Kara no Kyoukai 1 Fukan Fuukei",
            "Mirai Nikki",
            "FateZero 2nd Season",
            "Mahou Shoujo Madoka Magica",
            "Shingeki no Kyojin",
            "Ao no Exorcist",
            "Elfen Lied",
            "Magi The Labyrinth of Magic",
            "Darker than Black Kuro no Keiyakusha",
            "Btooom!",
            "Claymore",
            "Jormungand",
            "Kara no Kyoukai 5 Mujun Rasen",
            "Princess Lover!",
            "Shinsekai yori",
            "Toaru Majutsu no Index",
            "11eyes",
            "Danganronpa Kibou no Gakuen to Zetsubou no Koukousei - The Animation",
            "Fairy Tail",
            "Ginga Eiyuu Densetsu",
            "Hellsing Ultimate",
            "Higurashi no Naku Koro ni",
            "Kampfer",
            "Mai-HiME",
            "Mobile Suit Gundam Wing",
            "Neon Genesis Evangelion",
            "Phantom Requiem for the Phantom",
            "Psycho-Pass",
            "Yahari Ore no Seishun Love Comedy wa Machigatteiru.",
            "Arakawa Under the Bridge",
            "FLCL",
            "Katanagatari",
            "Hyouka",
            "Nisemonogatari",
            "Noragami",
            "Yojouhan Shinwa Taikei",
            "xxxHOLiC",
            "Denpa Onna to Seishun Otoko",
            "Kyoukai no Kanata",
            "Mononoke",
            "Kami nomi zo Shiru Sekai",
            "Nisekoi",
            "Mawaru Penguindrum",
            "Sayonara Zetsubou Sensei",
            "Angel Beats!",
            "Blood Lad",
            "Clannad",
            "Kokoro Connect",
            "Uchouten Kazoku",
            "ef A Tale of Memories.",
            "Eve no Jikan",
            "Inu to Hasami wa Tsukaiyou",
            "Mushishi",
            "Sankarea",
            "Tasogare Otome x Amnesia",
            "Arakawa Under the Bridge x Bridge",
            "Boku dake ga Inai Machi",
            "Steins;Gate",
            "Kono Subarashii Sekai ni Shukufuku wo!",
            "No Game No Life",
            "Higurashi no Naku Koro ni Kai",
            "Gate Jieitai Kanochi nite, Kaku Tatakaeri",
            "Ajin",
            "Endride",
            "Akame ga Kill!",
            "Hai to Gensou no Grimgar",
            "Ookami to Koushinryou",
            "Mondaiji-tachi ga Isekai kara Kuru Sou Desu yo",
            "Suzumiya Haruhi no Yuuutsu (2009)",
            "Ixion Saga DT",
            "Punch Line",
            "Sword Art Online",
            "Ushinawareta Mirai wo Motomete",
            "Gate Jieitai Kanochi nite",
            "Yahari Ore no Seishun Love Comedy wa Machigatteiru. Zoku",
            "Re:ZERO -Starting Life in Another World-",
            "JoJo's Bizarre Adventure",
            "Ano Hi Mita Hana no Namae wo Bokutachi wa Mada Shiranai.",
            "Sakamichi no Apollon",
            "Toradora!",
            "Nagi no Asukara",
            "Sakurasou no Pet na Kanojo",
            "Haikyuu!!",
            "Lovely Complex",
            "Suzuka",
            "Ao Haru Ride",
            "Nodame Cantabile",
            "Tokyo Ghoul",
            "Piano no Mori",
            "Bakuman.",
            "Kiniro no Corda Primo Passo",
            "Tari Tari",
            "Ookami to Koushinryou II",
            "Chuunibyou demo Koi ga Shitai!",
            "NHK ni Youkoso!",
            "Itazura na Kiss",
            "Kareshi Kanojo no Jijou",
            "School Days",
            "White Album 2",
            "Ghost Hunt",
            "Uchuu Kyoudai",
            "Shigatsu wa Kimi no Uso",
            "Nana",
            "Aoi Bungaku Series",
            "Chihayafuru",
            "Hyouge Mono",
            "Joshiraku",
            "Kaze Tachinu",
            "Natsume Yuujinchou",
            "Natsuyuki Rendezvous",
            "Ping Pong The Animation",
            "Saraiya Goyou",
            "Glass no Kamen (2005)",
            "Hanasaku Iroha",
            "K-On!",
            "Byousoku 5 Centimeter",
            "Kokurikozaka kara",
            "Clannad After Story",
            "Shouwa Monogatari",
            "Cowboy Bebop",
            "Kimi ni Todoke",
            "Little Busters!",
            "Gin no Saji",
            "Bokura ga Ita",
            "Peach Girl",
            "Skip Beat!",
            "Hachimitsu to Clover",
            "Kaikan Phrase",
            "Death Note",
            "Hana Yori Dango",
            "My Hero Academia",
            "Candy Candy",
            "Canvas 2 Niji-iro no Sketch",
            "Detroit Metal City",
            "Durarara!!",
            "Glass no Kamen",
            "Hachimitsu to Clover II",
            "Paradise Kiss",
            "Beck",
            "Ga-Rei Zero",
            "Gokinjo Monogatari",
            "Gravitation",
            "Panty & Stocking with Garterbelt",
            "Ai Shite Night",
            "Full Moon wo Sagashite",
            "Gankutsuou",
            "Ghost in the Shell Stand Alone Complex",
            "Devil Survivor 2 The Animation",
            "Hamatora The Animation",
            "RideBack",
            "Strike the Blood",
            "Tailenders",
            "Tengen Toppa Gurren Lagann",
            "Toaru Kagaku no Railgun",
            "K",
            "Kuroshitsuji",
            "Karneval",
            "Code Geass Hangyaku no Lelouch",
            "Hataraku Maou-sama!",
            "Bleach",
            "Accel World",
            "Hakkenden Touhou Hakken Ibun",
            "Baccano!",
            "Deadman Wonderland",
            "Free!",
            "No.6",
            "Soul Eater",
            "Zettai Karen Children The Unlimited - Hyoubu Kyousuke",
            "Guilty Crown",
            "Code Geass Hangyaku no Lelouch R2",
            "Kuroko no Basket 2nd Season",
            "Prince of Tennis",
            "Slam Dunk",
            "Eyeshield 21",
            "Diamond no Ace",
            "Katekyo Hitman Reborn!",
            "Nanatsu no Taizai",
            "Hajime no Ippo",
            "New Prince of Tennis",
            "Area no Kishi",
            "Yowamushi Pedal",
            "Cross Game",
            "Hikaru no Go",
            "Hunter x Hunter",
            "Inazuma Eleven",
            "Buzzer Beater",
            "Dear Boys",
            "I'llCKBC",
            "One Outs",
            "Saki",
            "Tiger & Bunny",
            "Ginga e Kickoff!!",
            "Ookiku Furikabutte",
            "Brothers Conflict",
            "InuYasha",
            "Pokemon",
            "Konjiki no Gash Bell!!",
            "Kekkaishi",
            "Keroro Gunsou",
            "Lupin III Part II",
            "Mouretsu Pirates",
            "Naruto",
            "One Piece",
            "Dragon Ball Z",
            "Dragon Ball",
            "Hunter x Hunter (2011)",
            "Naruto Shippuuden",
            "Fullmetal Alchemist",
            "Yu Yu Hakusho",
            "D.Gray-man",
            "Nabari no Ou",
            "Shijou Saikyou no Deshi Kenichi",
            "Yu-Gi-Oh! Duel Monsters",
            "Rekka no Honoo",
            "Blue Dragon",
            "Eureka Seven",
            "Gintama",
            "Kaze no Stigma",
            "Fushigi Yuugi",
            "Dragon Ball Kai",
            "Kino no Tabi The Beautiful World",
            "Mononoke Hime",
            "Hotarubi no Mori e",
            "Sen to Chihiro no Kamikakushi",
            "Bartender",
            "Haibane Renmei",
            "Aria the Animation",
            "Jigoku Shoujo",
            "Gake no Ue no Ponyo",
            "Kaze no Tani no Nausicaa",
            "Black Jack",
            "Final Fantasy The Spirits Within",
            "Genshiken",
            "Hotaru no Haka",
            "Kaiba",
            "Master Keaton",
            "Moyashimon",
            "Seirei no Moribito",
            "Shinreigari Ghost Hound",
            "Toki wo Kakeru Shoujo",
            "Suzumiya Haruhi no Shoushitsu",
            "Noein Mou Hitori no Kimi e",
            "Zetsuen no Tempest",
            "Arata naru Sekai Mirai-hen",
            "Higashi no Eden",
            "Persona 4 The Animation",
            "Suzumiya Haruhi no Yuuutsu",
            "Seikimatsu Occult Gakuin",
            "Robotics;Notes",
            "Another",
            "Hentai Ouji to Warawanai Neko.",
            "Serial Experiments Lain",
            "RahXephon",
            "Ergo Proxy",
            "Shoujo Kakumei Utena",
            "Bokurano",
            "Top wo Nerae! Gunbuster!",
            "Mobile Suit Gundam Seed",
            "Blue Gender",
            "Sousei no Aquarion",
            "The Big O",
            "Freezing",
            "Gilgamesh",
            "Mars of Destruction",
            "Soukyuu no Fafner Dead Aggressor",
            "Bakuretsu Tenshi",
            "Bakemonogatari",
            "Food Wars! Shokugeki no Soma",
            "Fate/Zero",
            "Fate/stay night",
            "Fate/stay night - Unlimited Blade Works",
            "Fate/kaleid liner Prisma Illya",
            "Sailor Moon",
            "Sailor Moon Sailor Stars",
            "Sailor Moon S",
            "Sailor Moon R",
            "Rurouni Kenshin",
            "Major S2",
            "Major S3",
            "Planetes",
            "Danshi Koukousei no Nichijou",
            "SKET Dance",
            "Bungo Stray Dogs",
            "Kaiji: Ultimate Survivor",
            "Kaiji: Against All Rules",
            "Rainbow: Nisha Rokubou no Shichinin",
            "Initial D Fourth Stage",
            "Initial D First Stage",
            "Initial D Second Stage",
            "Initial D Third Stage",
            "Golgo 13",
            "Trigun",
            "Ashita no Joe",
            "Death Parade",
            "Mobile Suit Gundam",
            "Detective Conan",
            "Assassination Classroom",
            "Princess Tutu",
            "Maid-Sama!",
            "Zankyou no Terror",
            "Gosick",
            "Kill la Kill",
            "Kingdom",
            "Shoujo Shuumatsu Ryokou",
            "Working!!!",
            "ef ~ A Tale of Melodies",
            "ef ~ A Tale of Memories",
            "Ginga Nagareboshi Gin",
            "Hanada Shounen-shi",
            "Kindaichi Shounen no Jikenbo",
            "School Rumble Ni Gakki",
            "Slayers Next",
            "City Hunter",
            "City Hunte 2",
            "City Hunter 3",
            "Kanon",
            "Nurarihyon no Mago: Sennen Makyou",
            "Touch",
            "Log Horizon",
            "Log Horizon 2",
            "Takarajima",
            "Ore Monogatari!!",
            "Kami nomi zo Shiru Sekai II",
            "Kami nomi zo Shiru Sekai I",
            "Overlord",
            "Sekaiichi Hatsukoi",
            "Sekaiichi Hatsukoi 2",
            "Kobato",
            "Ushio to Tora",
            "Azumanga Daioh",
            "Lucky Star",
            "Full Metal Panic!",
            "Eden of the past",
            "Nekomonogatari: Kuro",
            "Saiunkoku Monogatari",
            "Full Metal Panic! The Second Raid",
            "Penguindrum",
            "Beelzebub",
            "Plastic Memories",
            "Mobile Suit Gundam: Iron-Blooded Orphans",
            "Non Non Biyori",
            "Macross",
            "Mobile Suit Zeta Gundam",
            "Tanaka-kun wa Itsumo Kedaruge",
            "Terra e...",
            "Yakitate!! Japan",
            "Yuru Yuri San Hai!",
            "Jormungand: Perfect Order",
            "Shirokuma Cafe",
            "Zoku Sayonara Zetsubou Sensei",
            "Junjou Romantica",
            "Love Live! School Idol Project 2nd Season",
            "Hokuto no Ken",
            "Zan Sayonara Zetsubou Sensei",
            "Ginga Tetsudou 999",
            "Saint Seiya",
            "Seihou Bukyou Outlaw Star",
            "Hidamari Sketch x Honeycomb",
            "Working'!!",
            "Gungrave",
            "Konosuba",
            "High School DxD",
            "Dungeon ni Deai wo Motomeru no wa Machigatteiru Darou ka",
            "Noragami Aragoto",
            "Charlotte",
            "Owari no Seraph",
            "Tonari no Kaibutsu-kun",
            "Black Bullet",
            "Zero no Tsukaima",
            "Black Lagoon",
            "Boku wa Tomodachi ga Sukunai",
            "Mahouka Koukou no Rettousei",
            "Date A Live",
            "Gekkan Shoujo Nozaki-kun",
            "Ore no Imouto ga Konnani Kawaii Wake ga Nai",
            "Golden Time",
            "Kore wa Zombie Desu ka?",
            "Koutetsujou no Kabaneri",
            "Prison School",
            "Rosario to Vampire",
            "Gate: Jieitai Kanochi nite, Kaku Tatakaeri",
            "Kekkai Sensen",
            "Shakugan no Shana",
            "Watashi ga Motenai no wa Dou Kangaetemo Omaera ga Warui!",
            "Vampire Knight",
            "Trinity Seven",
            "Akatsuki no Yona",
            "Rakudai Kishi no Cavalry",
            "IS: Infinite Stratos",
            "Kiznaiver",
            "Aldnoah.Zero",
            "Rokka no Yuusha",
            "Yamada-kun to 7-nin no Majo",
            "Shimoneta to Iu Gainen ga Sonzai Shinai Taikutsu na Sekai",
            "Chobits",
            "Amagi Brilliant Park",
            "Problem Children Are Coming From Another World, Aren't They?",
            "Chuunibyou demo Koi ga Shitai! Ren",
            "Monster Musume no Iru Nichijou",
            "Sakamoto desu ga?",
            "Sora no Otoshimono",
            "Fruits Basket",
            "Boku wa Tomodachi ga Sukunai Next",
            "Gangsta.",
            "God Eater",
            "Himouto! Umaru-chan",
            "Grisaia no Kajitsu",
            "Rurouni Kenshin: Meiji Kenkaku Romantan",
            "Gakusen Toshi Asterisk",
            "Mayo Chiki!",
            "Saenai Heroine no Sodatekata",
            "Blood+",
            "Tokyo Ravens",
            "Sekirei",
            "Shingeki no Bahamut: Genesis",
            "Gamers!",
            "Maoyuu Maou Yuusha",
            "Hidan no Aria",
            "Hitsugi no Chaika",
            "Dagashi Kashi",
            "Ano Natsu de Matteru",
            "Aesthetica of a Rogue Hero",
            "Suisei no Gargantia",
            "Shaman King",
            "Air",
            "Gakkougurashi!",
            "Arslan Senki",
            "Gantz",
            "Ookami Shoujo to Kuro Ouji",
            "Gokukoku no Brynhildr",
            "Wolf's Rain",
            "Haiyore! Nyaruko-san",
            "Hibike! Euphonium",
            "Seto no Hanayome",
            "Amagami SS",
            "C: The Money of Soul and Possibility Control",
            "Devilman",
            "Seikon no Qwaser",
            "Mikakunin de Shinkoukei",
            "Shuffle!",
            "To LOVE-Ru Darkness",
            "Speed Grapher",
            "Tales of the Abyss",
            "Zegapain",
            "Psychic Squad",
            "Afro Samurai",
            "Funny Faces in High School",
            "Chaika -The Coffin Princess-",
            "Wolf Girl and Black Prince",
            "Twin Star Exorcists",
            "Strawberry Panic",
            "Trinity Blood",
            "Blue Comet SPT LayznerAsura Cryin' 2",
            "Bamboo Blade",
            "Cuticle Detective Inaba",
            "Haha wo Tazunete Sanzenri",
            "Nyarko-san: Another Crawling Chaos W",
            "Hanamaru Kindergarten",
            "Mahoraba: Heartful days",
            "Heaven's Memo Pad",
            "Dragon Ball Z Kai",
            "Honey and Clover",
            "Undefeated Bahamut Chronicle",
            "Shinmai Maou no Testament Burst",
            "Tsubasa Chronicle 2nd Series",
            "MM!",
            "Silver Spoon",
            "Tsubasa Chronicle",
            "Hundred",
            "Case Closed",
            "Wagnaria!!2",
            "Kanokon: The Girl Who Cried Fox",
            "Knights of Sidonia",
            "Hayate the Combat Butler",
            "Omamori Himari",
            "AntiMagic Academy 35th Test Platoon",
            "Sunday Without God",
            "World Trigger",
            "Berserk",
            "Good Luck Girl!",
            "World Break: Aria of Curse for a Holy Swordsman",
            "The Lost Village",
            "The Legend of the Legendary Heroes",
            "Tamako Market",
            "The Asterisk War: The Academy City on the Water",
            "Kotoura-san",
            "Little Busters!: Refrain",
            "Lord Marksman and Vanadis",
            "Nyan Koi!",
            "Kiss x Sis",
            "Dusk Maiden of Amnesia",
            "Amnesia",
            "Tanaka-kun is Always Listless",
            "Ga-Rei-Zero",
            "K: Return of Kings",
            "KenIchi: The Mightiest Disciple",
            "Riddle Story of Devil",
            "Chrono Crusade",
            "Tokyo Magnitude 8.0",
            "Majikoi: Oh! Samurai Girls",
            "Nura: Rise of the Yokai Clan",
            "Engaged to the Unidentified",
            "Outbreak Company",
            "Ground Control to Psychoelectric Girl",
            "Unbreakable Machine-Doll",
            "Heaven's Lost Property: Forte",
            "One Week Friends",
            "JoJo's Bizarre Adventure: Diamond is Unbreakable",
            "The Tatami Galaxy",
            "xxxHOLiC Kei",
            "Jormungand Season 2: Perfect Order",
            "Paranoia Agent",
            "Durarara!! x2",
            "Grisaia no Rakuen",
            "Okami-San and Her Seven Companions",
            "Dimension W",
            "Love Hina",
            "Black Cat",
            "Code:Breaker",
            "A Certain Scientific Railgun S",
            "Motto To LOVE-Ru",
            "The Ambition of Oda Nobuna",
            "Black Butler: Book of Circus",
    };
    private AutoCompleteTextView editText;
    private AdView adView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        MobileAds.initialize(this, "ca-app-pub-3051959948788495~1052340378");
        adView = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        editText = findViewById(R.id.autoCompleteTextView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,animes);
        editText.setAdapter(adapter);
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
        nextButton();
    }
    public void nextButton(){
        Button buton = (Button)findViewById(R.id.angry_btn);
        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Activitatea2.class);
                intent.putExtra("Anime",editText.getText().toString());
                startActivity(intent);
            }
        });
    }
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
