## SetMealTimePopupController.cs

```
using UnityEngine;

public class SetMealTimePopupController : MonoBehaviour
{
    [SerializeField] private GameObject setMealTimePopup;
    [SerializeField] private MealTimeSetter mealTimeSetter; // MealTimeSetter에 대한 참조 추가

    public void PopupOpen()
    {
        setMealTimePopup.SetActive(true);

    }

    public void PopupClose()
    {
        setMealTimePopup.SetActive(false);
        mealTimeSetter.ClearWarningText(); // warningText 초기화
    }
}

```
