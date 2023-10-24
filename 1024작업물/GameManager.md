## GameManager.cs

```
using UnityEngine;
using UnityEngine.UI;
using TMPro;

//유저 정보 클래스
[System.Serializable]
public class UserData
{
    public int id; //아이디(각종 api 접근용)
    [SerializeField] public float exp; //경험치
    [SerializeField] public string name; //설정한 이름;
    [SerializeField] public string[] mealTimes = new string[3]; // 밥 먹는 시간을 문자열 배열로 저장
}

public class GameManager : MonoBehaviour
{
    //게임 매니저 싱글톤
    private static GameManager _instance;
    public static GameManager Instance
    {
        get
        {
            if (_instance == null)
            {
                _instance = FindObjectOfType<GameManager>();
            }
            return _instance;
        }
    }

    //유저 경험치
    //public int exp { get; private set; } //접근은 가능하지만 값 변경은 현재클래스에서만 가능
    [SerializeField] private UserData userData = new UserData(); //유저데이터

    public Material[] backgroundMaterials; // 배경용 Material 배열
    public GameObject targetPrefab; // 변경할 prefab

    private int selectedBackgroundNumber = 0; // 선택된 배경 번호. 기본값은 0. PlayerPrefs에서 로드됩니다.

    private const string BACKGROUND_NUMBER_KEY = "SelectedBackgroundNumber";

    private const string USER_NAME_KEY = "UserName"; // PlayerPrefs에 저장할 키를 정의

    public TextMeshProUGUI userNameTMPText; // TextMeshProUGUI 컴포넌트에 대한 참조
    public TextMeshProUGUI[] mealTimeTexts; // 각 MealTime 값을 표시할 TextMeshProUGUI 배열

    private const string MEAL_TIME_KEY_PREFIX = "MealTime_"; // PlayerPrefs에 저장할 키의 접두사

    public Image[] mealTimeImages; // 각 MealTime에 대응하는 이미지 배열


    private void Awake()
    {
        //게임 매니저 인스턴스를 하나만 유지
        if (_instance == null)
        {
            _instance = this;
            DontDestroyOnLoad(gameObject); //씬 전환 시 사라지지 않음
        }
        else if (_instance == this)
        {
            Destroy(gameObject);
        }
    }

    private void Start()
    {
        //TODO: 세팅된 데이터가 있는지 확인 없다면 세팅하기

        // PlayerPrefs에서 저장된 값을 로드합니다.
        if (PlayerPrefs.HasKey(BACKGROUND_NUMBER_KEY))
        {
            selectedBackgroundNumber = PlayerPrefs.GetInt(BACKGROUND_NUMBER_KEY);
        }

        // 시작할 때 PlayerPrefs에서 이름을 로드
        if (PlayerPrefs.HasKey(USER_NAME_KEY))
        {
            userData.name = PlayerPrefs.GetString(USER_NAME_KEY);
            UpdateUserNameUI(); // UI 업데이트
        }
        // MealTimes 로드
        for (int i = 0; i < userData.mealTimes.Length; i++)
        {
            if (PlayerPrefs.HasKey(MEAL_TIME_KEY_PREFIX + i))
            {
                userData.mealTimes[i] = PlayerPrefs.GetString(MEAL_TIME_KEY_PREFIX + i);
            }
        }

        ApplyBackgroundMaterial();
        UpdateMealTimesUI(); // UI 업데이트
    }

    private void Update()
    {

    }

    public void ApplyBackgroundMaterial()
    {
        // targetPrefab에 속한 모든 Renderer 컴포넌트를 검색합니다.
        Renderer[] renderers = targetPrefab.GetComponentsInChildren<Renderer>();

        foreach (Renderer rend in renderers)
        {
            // 각 Renderer의 Material을 바꿉니다.
            rend.material = backgroundMaterials[selectedBackgroundNumber];
        }
    }

    // 버튼 클릭을 위한 메서드
    public void OnButtonClicked(int backgroundIndex)
    {
        selectedBackgroundNumber = backgroundIndex;

        // 선택된 번호를 PlayerPrefs에 저장합니다.
        PlayerPrefs.SetInt(BACKGROUND_NUMBER_KEY, selectedBackgroundNumber);
        PlayerPrefs.Save();

        ApplyBackgroundMaterial();
    }

    public float GetExp()
    {
        return userData.exp;
    }

    //경험치 획득
    public void AddExp(float amount)
    {
        userData.exp += amount;
    }

    // 이름을 저장하고 로드하는 메서드 추가
    public void SaveUserName(string newName)
    {
        userData.name = newName;
        PlayerPrefs.SetString(USER_NAME_KEY, newName);
        PlayerPrefs.Save();

        UpdateUserNameUI(); // UI 업데이트
    }

    public string GetUserName()
    {
        return userData.name;
    }

    public void UpdateUserNameUI()
    {
        if (userNameTMPText != null)
        {
            userNameTMPText.text = userData.name;
        }
    }

    public void UpdateMealTimesUI()
    {
        string[] currentMealTimes = GetMealTimes();
        for (int i = 0; i < currentMealTimes.Length && i < mealTimeTexts.Length; i++)
        {
            if (string.IsNullOrEmpty(currentMealTimes[i]))
            {
                mealTimeTexts[i].text = "";
                mealTimeImages[i].gameObject.SetActive(false); // 이미지 숨기기
            }
            else
            {
                mealTimeTexts[i].text = currentMealTimes[i];
                mealTimeImages[i].gameObject.SetActive(true); // 이미지 표시
            }
        }
    }

    // MealTimes를 저장하는 메서드
    public void SaveMealTimes(string[] times)
    {
        for (int i = 0; i < times.Length && i < userData.mealTimes.Length; i++)
        {
            if (!string.IsNullOrEmpty(times[i]))
            {
                userData.mealTimes[i] = times[i];
                PlayerPrefs.SetString(MEAL_TIME_KEY_PREFIX + i, times[i]);
            }
        }
        PlayerPrefs.Save();

        // UI 업데이트
        UpdateMealTimesUI();
        SortMealTimes();
    }


    // MealTimes를 가져오는 메서드
    public string[] GetMealTimes()
    {
        return userData.mealTimes;
    }

    public void RemoveMealTime(int index)
    {
        if (index >= 0 && index < userData.mealTimes.Length - 1) // 마지막 인덱스 전까지만
        {
            // 삭제된 인덱스 이후의 모든 항목을 한 칸씩 앞으로 이동
            for (int i = index; i < userData.mealTimes.Length - 1; i++)
            {
                userData.mealTimes[i] = userData.mealTimes[i + 1];
            }
        }

        // 마지막 항목을 빈 문자열로 설정
        userData.mealTimes[userData.mealTimes.Length - 1] = "";

        // PlayerPrefs에서 해당 인덱스의 MealTime 삭제
        PlayerPrefs.DeleteKey(MEAL_TIME_KEY_PREFIX + (userData.mealTimes.Length - 1));

        UpdateMealTimesUI(); // UI 업데이트
        SortMealTimes();
    }


    public void SortMealTimes()
    {
        System.Array.Sort(userData.mealTimes, (x, y) =>
        {
            // 빈 문자열 처리
            if (string.IsNullOrEmpty(x)) return 1;
            if (string.IsNullOrEmpty(y)) return -1;

            // 시간과 분 추출
            int hourX = int.Parse(x.Substring(0, 2));
            int minuteX = int.Parse(x.Substring(3, 2));

            int hourY = int.Parse(y.Substring(0, 2));
            int minuteY = int.Parse(y.Substring(3, 2));

            // 시간 비교
            if (hourX < hourY) return -1;
            if (hourX > hourY) return 1;

            // 분 비교
            if (minuteX < minuteY) return -1;
            if (minuteX > minuteY) return 1;

            return 0;
        });

        UpdateMealTimesUI(); // 정렬 후 UI 업데이트
    }
}

```
