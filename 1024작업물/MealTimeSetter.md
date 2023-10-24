## MealTimeSetter.cs

```
using UnityEngine;
using UnityEngine.UI;
using TMPro;

public class MealTimeSetter : MonoBehaviour
{
    public TMP_InputField hourInputField; // 시간을 입력받을 TMP_InputField
    public TMP_InputField minuteInputField; // 분을 입력받을 TMP_InputField
    public Button saveButton; // 시간을 저장할 버튼
    public TextMeshProUGUI warningText; // 경고 메세지를 표시할 TextMeshPro
    public SetMealTimePopupController popupController; // 팝업 컨트롤러에 대한 참조

    private void Start()
    {
        // 버튼에 기능 연결
        saveButton.onClick.AddListener(SaveMealTime);
    }

    public void SaveMealTime()
    {
        // 입력된 시간과 분을 파싱
        int hour, minute;
        if (int.TryParse(hourInputField.text, out hour) && int.TryParse(minuteInputField.text, out minute))
        {
            // 유효한 시간과 분 범위 확인
            if (hour >= 0 && hour <= 23 && minute >= 0 && minute <= 59)
            {
                string mealTime = hour.ToString("00") + "시 " + minute.ToString("00") + "분";
                string[] currentMealTimes = GameManager.Instance.GetMealTimes();

                bool isAdded = false;
                for (int i = 0; i < currentMealTimes.Length; i++)
                {
                    if (string.IsNullOrEmpty(currentMealTimes[i]))
                    {
                        currentMealTimes[i] = mealTime;
                        isAdded = true;
                        break;
                    }
                }

                if (!isAdded)
                {
                    warningText.text = "더 이상 추가할 수 없어요!";
                    return;
                }

                GameManager.Instance.SaveMealTimes(currentMealTimes);

                // MealTime 저장 후 팝업 닫기
                popupController.PopupClose();

                // InputField의 값을 초기화
                hourInputField.text = "";
                minuteInputField.text = "";
            }
            else
            {
                warningText.text = "유효하지 않은 시간이에요!";
            }
        }
        else
        {
            warningText.text = "시간을 올바르게 입력해주세요!";
        }
    }

    public void ClearWarningText()
    {
        warningText.text = ""; // warningText 초기화
    }
}

```
