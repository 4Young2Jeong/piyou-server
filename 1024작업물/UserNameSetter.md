## UserNameSetter.cs

```
using UnityEngine;
using UnityEngine.UI;
using TMPro;

public class UserNameSetter : MonoBehaviour
{
    public TMP_InputField nameInputField; // 이름을 입력받을 TMP_InputField
    public Button saveButton; // 이름을 저장할 버튼
    public SetNamePopup setNamePopup; // 팝업창 스크립트에 대한 참조

    private void Start()
    {
        // 초기화: 현재 저장된 이름을 입력 필드에 표시
        nameInputField.text = GameManager.Instance.GetUserName();

        // 버튼에 기능 연결
        saveButton.onClick.AddListener(SaveUserName);
    }

    public void SaveUserName()
    {
        string userName = nameInputField.text;
        GameManager.Instance.SaveUserName(userName);

        nameInputField.text = "";

        // 이름을 저장한 후 팝업창 닫기
        setNamePopup.PopupClose();

    }
}

```
