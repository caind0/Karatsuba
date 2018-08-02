/**
 *
 * @author Cain Do
 */
/* Implementation of Karatsuba Multiplication Algorithm O(n^log3) = O(n^1.584..)
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Scanner;
import java.math.BigInteger;

public class karatsuba {

    /**
     * @param x
     * @param y
     * @return 
     */
    
    private final static BigInteger ZERO = new BigInteger("0");
    
    public static BigInteger karatsuba(BigInteger x, BigInteger y)
    {
        
        
        //find the maximum length between the two numbers
        int N = Math.max(x.bitLength(),y.bitLength());
        //if number of digits is less than 8 multiply without karatsuba algo.
        if (N <= 8) return x.multiply(y);
        
        //max length divided and rounded
        N=(N/2)+(N%2);
        
        
        //Split the first term in two parts
        BigInteger b = x.shiftRight(N);
        BigInteger a = x.subtract(b.shiftLeft(N));
        //Split the second term in two parts
        BigInteger d = y.shiftRight(N);
        BigInteger c = y.subtract(d.shiftLeft(N));
        
        //--compute sub expressions--//
        
        //Step 1 recursively compute ac
        BigInteger ac    = karatsuba(a, c);
        //Step 2 recursively compute bd
        BigInteger bd    = karatsuba(b, d);
        //Step 3 recursively compute (a+b)(c+d)
        BigInteger abcd  = karatsuba(a.add(b), c.add(d));
        
        //Gauss' Trick (Step 3) - (Step 1) - (Step 2) = ad+bc
        return ac.add(abcd.subtract(ac).subtract(bd).shiftLeft(N)).add(bd.shiftLeft(2*N));    }
    
    
    
    public static void main(String[] args) {
        
        
        System.out.println("<--Karatsuba Algorithm-->");
        
        //Accept two Big Integers
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the first number \n");
        BigInteger n1 = scan.nextBigInteger();
        System.out.println("Enter the second number \n");
        BigInteger n2 = scan.nextBigInteger();
        
        //Call karatsuba method
        BigInteger result=karatsuba(n1,n2);
        System.out.println("Result: "+result);
    
    }
    
}
