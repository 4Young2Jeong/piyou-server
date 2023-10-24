## InputRangeChecker_Minute.cs

```
using UnityEngine;
using TMPro;

public class InputRangeChecker_Minute : MonoBehaviour
{
    private TMP_InputField inputField;

    private void Awake()
    {
        inputField = GetComponent<TMP_InputField>();
        inputField.onValueChanged.AddListener(ValidateInput);
    }

    private void ValidateInput(string input)
    {
        if (string.IsNullOrEmpty(input))
            return;

        int value;
        if (int.TryParse(input, out value))
        {
            if (value == 0 && input.Length > 1) // 0을 입력하고 길이가 1보다 크면
            {
                inputField.text = "0"; // 0으로 다시 설정
                return;
            }

            if (value < 0 || value > 59)
            {
                inputField.text = Mathf.Clamp(value, 0, 59).ToString();
            }
        }
        else
        {
            inputField.text = "";
        }
    }

    public void ClearInputField()
    {
        inputField.text = "";
    }
}

```
