## SetNamePopup.cs

```
using UnityEngine;
using UnityEngine.UI;
using TMPro; // TextMeshPro를 사용할 경우 추가

public class SetNamePopup : MonoBehaviour
{
    [SerializeField] private GameObject setNamePopup;

    public TMP_InputField targetInputField;

    public void PopupOpen()
    {
        setNamePopup.SetActive(true);

    }

    public void PopupClose()
    {
        setNamePopup.SetActive(false);
        targetInputField.text = "";
    }
}

```
