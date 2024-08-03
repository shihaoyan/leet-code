package com.shy.code3;

import com.shy.common.ListNode;

/**
 * @author 石皓岩
 * @createDate 2020-09-15 8:17
 * 描述:重新排列日志文件
 * 你有一个日志数组 logs。每条日志都是以空格分隔的字串。
 * <p>
 * 对于每条日志，其第一个字为字母与数字混合的 标识符 ，除标识符之外的所有字为这一条日志的 内容 。
 * <p>
 * 除标识符之外，所有字均由小写字母组成的，称为 字母日志
 * 除标识符之外，所有字均由数字组成的，称为 数字日志
 * 题目所用数据保证每个日志在其标识符后面至少有一个字。
 * <p>
 * 请按下述规则将日志重新排序：
 * <p>
 * 所有 字母日志 都排在 数字日志 之前。
 * 字母日志 在内容不同时，忽略标识符后，按内容字母顺序排序；在内容相同时，按标识符排序；
 * 数字日志 应该按原来的顺序排列。
 * 返回日志的最终顺序。
 * <p>
 *  
 * <p>
 * 示例 ：
 * <p>
 * 输入：["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]
 * 输出：["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= logs.length <= 100
 * 3 <= logs[i].length <= 100
 * logs[i] 保证有一个标识符，并且标识符后面有一个字。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reorder-data-in-log-files
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MainCode3 {


}
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {

    public static void main(String[] args) {
        ListNode head5 = new ListNode(5, null);
        ListNode head4 = new ListNode(3, head5);
        ListNode head3 = new ListNode(3, head4);
        ListNode head2 = new ListNode(2, head3);
        ListNode head1 = new ListNode(1, head2);
        ListNode listNode = removeNthFromEnd(head1, 2);
        System.out.println(listNode.val);


    }
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null){
            return null;
        }
        // 创建一个节点给他连起来，防止头被删除
        ListNode res = new ListNode();
        res.next = head;

        // 准备快慢指针，快指针比慢指针先走n+1个位置
        ListNode n1 = head;
        ListNode n2 = head;
        int i = 0;
        while(i < n && n2.next != null){
            n2 = n2.next;
            i++;
        }
        while(n2.next != null && n1.next != null){
            n1 = n1.next;
            n2 = n2.next;
        }
        n1.next = n1.next.next;
        //  开始删除
        return res.next;
    }
}
